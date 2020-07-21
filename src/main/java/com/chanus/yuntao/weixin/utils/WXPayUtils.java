/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.weixin.utils;

import com.chanus.yuntao.utils.core.IOUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.encrypt.HMACUtils;
import com.chanus.yuntao.utils.core.encrypt.MD5Utils;
import com.chanus.yuntao.weixin.pay.constants.WXPayConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 微信支付工具类
 *
 * @author Chanus
 * @date 2020-07-21 10:41:12
 * @since 1.1.0
 */
public class WXPayUtils {
    private static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        documentBuilderFactory.setXIncludeAware(false);
        documentBuilderFactory.setExpandEntityReferences(false);

        return documentBuilderFactory.newDocumentBuilder();
    }

    private static Document newDocument() throws ParserConfigurationException {
        return newDocumentBuilder().newDocument();
    }

    /**
     * XML 格式字符串转换为 Map
     *
     * @param strXML XML 字符串
     * @return XML 数据转换后的 Map
     */
    public static Map<String, Object> xmlToMap(String strXML) {
        InputStream stream = null;
        try {
            Map<String, Object> data = new HashMap<>();
            DocumentBuilder documentBuilder = newDocumentBuilder();
            stream = new ByteArrayInputStream(strXML.getBytes(StandardCharsets.UTF_8));
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }

            return data;
        } catch (Exception e) {
            throw new RuntimeException("convert xml to map error.", e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }

    /**
     * 将 Map 转换为 XML 格式的字符串
     *
     * @param map Map 类型数据
     * @return XML 格式的字符串
     */
    public static String mapToXml(Map<String, Object> map) {
        StringWriter writer = new StringWriter();
        try {
            org.w3c.dom.Document document = newDocument();
            org.w3c.dom.Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key : map.keySet()) {
                String value = map.get(key).toString();
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                org.w3c.dom.Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        } catch (Exception e) {
            throw new RuntimeException("convert map to xml error.", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 生成带有 sign 的 XML 格式字符串。使用 MD5 签名
     *
     * @param map Map 类型数据
     * @param key API 密钥
     * @return 含有 sign 字段的 XML
     */
    public static String generateSignedXml(final Map<String, Object> map, String key) {
        return generateSignedXml(map, key, WXPayConstants.MD5);
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param map      Map 类型数据
     * @param key      API 密钥
     * @param signType 签名类型
     * @return 含有 sign 字段的 XML
     */
    public static String generateSignedXml(final Map<String, Object> map, String key, String signType) {
        String sign = generateSignature(map, key, signType);
        map.put(WXPayConstants.FIELD_SIGN, sign);
        return mapToXml(map);
    }


    /**
     * 判断签名是否正确。使用 MD5 签名
     *
     * @param xmlStr XML 格式数据
     * @param key    API 密钥
     * @return 签名是否正确，{@code true} 签名正确，{@code false} 签名不正确
     */
    public static boolean isSignatureValid(String xmlStr, String key) {
        Map<String, Object> map = xmlToMap(xmlStr);
        if (!map.containsKey(WXPayConstants.FIELD_SIGN)) {
            return false;
        }
        String sign = map.get(WXPayConstants.FIELD_SIGN).toString();
        return generateSignature(map, key).equals(sign);
    }

    /**
     * 判断签名是否正确，必须包含 sign 字段，否则返回 false。使用 MD5 签名
     *
     * @param map Map 类型数据
     * @param key API 密钥
     * @return 签名是否正确，{@code true} 签名正确，{@code false} 签名不正确
     */
    public static boolean isSignatureValid(Map<String, Object> map, String key) {
        return isSignatureValid(map, key, WXPayConstants.MD5);
    }

    /**
     * 判断签名是否正确，必须包含 sign 字段，否则返回 false
     *
     * @param map      Map 类型数据
     * @param key      API 密钥
     * @param signType 签名方式
     * @return 签名是否正确，{@code true} 签名正确，{@code false} 签名不正确
     */
    public static boolean isSignatureValid(Map<String, Object> map, String key, String signType) {
        if (!map.containsKey(WXPayConstants.FIELD_SIGN)) {
            return false;
        }
        String sign = map.get(WXPayConstants.FIELD_SIGN).toString();
        return generateSignature(map, key, signType).equals(sign);
    }

    /**
     * 生成签名。使用 MD5 签名
     *
     * @param map 待签名数据
     * @param key API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, Object> map, String key) {
        return generateSignature(map, key, WXPayConstants.MD5);
    }

    /**
     * 生成签名. 注意，若含有 sign_type 字段，必须和 signType 参数保持一致
     *
     * @param map      待签名数据
     * @param key      API 密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, Object> map, String key, String signType) {
        Set<String> keySet = map.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (StringUtils.isNotBlank(map.get(k).toString())) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(map.get(k).toString().trim()).append("&");
        }
        sb.append("key=").append(key);
        if (WXPayConstants.MD5.equals(signType)) {
            return MD5Utils.md5(sb.toString()).toUpperCase();
        } else if (WXPayConstants.HMACSHA256.equals(signType)) {
            return HMACUtils.hmacSHA256(sb.toString(), key).toUpperCase();
        } else {
            throw new RuntimeException(String.format("Invalid sign_type: %s", signType));
        }
    }
}

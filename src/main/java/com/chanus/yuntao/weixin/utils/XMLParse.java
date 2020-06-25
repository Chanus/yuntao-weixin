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

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供提取消息格式中的密文及生成回复消息格式的接口
 *
 * @author Chanus
 * @date 2020-05-20 08:45:57
 * @since 1.0.0
 */
public class XMLParse {
    /**
     * 提取出 xml 数据包中的加密消息
     *
     * @param xml 待提取的 xml 字符串
     * @return 提取出的加密消息字符串
     */
    public static Object[] extract(String xml) {
        Object[] result = new Object[3];
        Document doc;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element root = doc.getRootElement();

            Element encryptElement = root.element("Encrypt");
            Element toUserNameElement = root.element("ToUserName");

            result[0] = 0;
            result[1] = encryptElement == null ? null : encryptElement.getStringValue();
            result[2] = toUserNameElement == null ? null : toUserNameElement.getStringValue();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 将微信后台推送的 xml 内容转换成 Map
     *
     * @param xml xml 字符串
     * @return Map
     */
    public static Map<String, String> parseXmlToMap(String xml) {
        Map<String, String> map = new HashMap<>();
        Document doc;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element root = doc.getRootElement();

            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 生成 xml 消息
     *
     * @param encrypt   加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @return 生成的 xml 字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {

        String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
                + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
                + "<TimeStamp>%3$s</TimeStamp>\n"
                + "<Nonce><![CDATA[%4$s]]></Nonce>\n"
                + "</xml>";

        return String.format(format, encrypt, signature, timestamp, nonce);
    }
}

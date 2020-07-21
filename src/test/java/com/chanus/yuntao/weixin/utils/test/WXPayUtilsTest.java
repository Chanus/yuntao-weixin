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
package com.chanus.yuntao.weixin.utils.test;

import com.chanus.yuntao.weixin.pay.constants.WXPayConstants;
import com.chanus.yuntao.weixin.utils.WXPayUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * WXPayUtils 测试
 *
 * @author Chanus
 * @date 2020-07-21 11:26:25
 * @since 1.1.0
 */
public class WXPayUtilsTest {
    @Test
    public void mapToXmlTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "wx2421b1c4370ec43b");
        map.put("attach", "支付测试");
        map.put("body", "JSAPI支付测试");
        map.put("mch_id", "10000100");
        map.put("detail", "{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }");
        map.put("nonce_str", "1add1a30ac87aa2db72f57a2375d8fec");
        map.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        map.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        map.put("out_trade_no", "1415659990");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "JSAPI");
        map.put("sign", "0CB01533B8C1EF103065174F50BCA001");
        String xmlStr = WXPayUtils.mapToXml(map);
        System.out.println(xmlStr);
    }

    @Test
    public void generateSignedXmlTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "wx2421b1c4370ec43b");
        map.put("attach", "支付测试");
        map.put("body", "JSAPI支付测试");
        map.put("mch_id", "10000100");
        map.put("detail", "{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }");
        map.put("nonce_str", "1add1a30ac87aa2db72f57a2375d8fec");
        map.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        map.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        map.put("out_trade_no", "1415659990");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "JSAPI");
        String xmlStr = WXPayUtils.generateSignedXml(map, "123456789");
        System.out.println(xmlStr);

        String xmlStr2 = WXPayUtils.generateSignedXml(map, "123456789", WXPayConstants.HMACSHA256);
        System.out.println(xmlStr2);
    }

    @Test
    public void isSignatureValidTest() {
        String key = "123456789";
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>\n" +
                "<openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>\n" +
                "<sign>17DE9B6C1B8C9C5C7986E80F157F48FA</sign>\n" +
                "<body>JSAPI支付测试</body>\n" +
                "<mch_id>10000100</mch_id>\n" +
                "<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>\n" +
                "<spbill_create_ip>14.23.150.211</spbill_create_ip>\n" +
                "<out_trade_no>1415659990</out_trade_no>\n" +
                "<appid>wx2421b1c4370ec43b</appid>\n" +
                "<total_fee>1</total_fee>\n" +
                "<trade_type>JSAPI</trade_type>\n" +
                "<attach>支付测试</attach>\n" +
                "<detail>{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }</detail>\n" +
                "</xml>";
        System.out.println(WXPayUtils.isSignatureValid(xmlStr, key));

        Map<String, Object> map = new HashMap<>();
        map.put("appid", "wx2421b1c4370ec43b");
        map.put("attach", "支付测试");
        map.put("body", "JSAPI支付测试");
        map.put("mch_id", "10000100");
        map.put("detail", "{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }");
        map.put("nonce_str", "1add1a30ac87aa2db72f57a2375d8fec");
        map.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        map.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        map.put("out_trade_no", "1415659990");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "JSAPI");
        map.put("sign", "17DE9B6C1B8C9C5C7986E80F157F48FA");
        System.out.println(WXPayUtils.isSignatureValid(map, key));
        map.put("sign", "84B71E4494DA800EBED0682789735BB652082ADD103242052E9171ABDA338450");
        System.out.println(WXPayUtils.isSignatureValid(map, key, WXPayConstants.HMACSHA256));
    }

    @Test
    public void generateSignatureTest() {
        String key = "123456789";
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "wx2421b1c4370ec43b");
        map.put("attach", "支付测试");
        map.put("body", "JSAPI支付测试");
        map.put("mch_id", "10000100");
        map.put("detail", "{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }");
        map.put("nonce_str", "1add1a30ac87aa2db72f57a2375d8fec");
        map.put("notify_url", "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
        map.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        map.put("out_trade_no", "1415659990");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "JSAPI");
        System.out.println(WXPayUtils.generateSignature(map, key));
        System.out.println(WXPayUtils.generateSignature(map, key, WXPayConstants.HMACSHA256));
    }
}

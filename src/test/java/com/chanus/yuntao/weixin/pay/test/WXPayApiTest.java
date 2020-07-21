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
package com.chanus.yuntao.weixin.pay.test;

import com.chanus.yuntao.utils.core.RandomUtils;
import com.chanus.yuntao.weixin.pay.WXPayApi;
import com.chanus.yuntao.weixin.pay.constants.WXPayConstants;
import com.chanus.yuntao.weixin.pay.enums.TradeTypeEnum;
import com.chanus.yuntao.weixin.pay.request.WXPayOrderQueryRequest;
import com.chanus.yuntao.weixin.pay.request.WXPayUnifiedorderRequest;
import org.junit.Test;

import java.util.Map;

/**
 * WXPayApi 测试
 *
 * @author Chanus
 * @date 2020-07-21 09:53:39
 * @since 1.1.0
 */
public class WXPayApiTest {
    @Test
    public void WXPayUnifiedorderRequestTest() {
        WXPayUnifiedorderRequest wxPayUnifiedorderRequest = WXPayUnifiedorderRequest.create()
                .setAppid("wx4c0b9bf56ed6df6a").setMch_id("1497389862")
                .setNonce_str(RandomUtils.getRandomNormalString(32))
                .setSign_type(WXPayConstants.MD5).setBody("JSAPI支付测试")
                .setDetail("{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }")
                .setAttach("支付测试").setOut_trade_no(RandomUtils.getRandomUniqueNo())
                .setTotal_fee("10000").setSpbill_create_ip("127.0.0.1")
                .setNotify_url("http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php")
                .setTrade_type(TradeTypeEnum.JSAPI.name())
                .setOpenid("oThyXtzi1FqPfKfM4MQYX51_c9ng");
        Map<String, Object> map = wxPayUnifiedorderRequest.toMap();
        map.forEach((k, v) -> System.out.println(k + "===" + v.toString()));

        System.out.println("---------------------------------------------------------------");
        System.out.println(wxPayUnifiedorderRequest.toXml());

        System.out.println("---------------------------------------------------------------");
        System.out.println(wxPayUnifiedorderRequest.toSignedXml("123456789"));
    }

    @Test
    public void unifiedOrderTest() {
        String orderId = RandomUtils.getRandomUniqueNo();
        System.out.println("orderId = " + orderId);
        WXPayUnifiedorderRequest wxPayUnifiedorderRequest = WXPayUnifiedorderRequest.create()
                .setAppid("wx4c0b9bf56ed6df6a").setMch_id("1497389862")
                .setNonce_str(RandomUtils.getRandomNormalString(32))
                .setSign_type(WXPayConstants.HMACSHA256).setBody("JSAPI支付测试")
                .setAttach("支付测试").setOut_trade_no(orderId)
                .setTotal_fee("10000").setSpbill_create_ip("127.0.0.1")
                .setNotify_url("http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php")
                .setTrade_type(TradeTypeEnum.JSAPI.name())
                .setOpenid("oThyXtzi1FqPfKfM4MQYX51_c9ng");
        String key = "172b42814464710cf4210d8ca87bf0d6";

        Map<String, Object> result = WXPayApi.unifiedOrder(wxPayUnifiedorderRequest, key);
        result.forEach((k, v) -> System.out.println(k + "===" + v));
    }

    @Test
    public void orderQueryTest() {
        WXPayOrderQueryRequest wxPayOrderQueryRequest = WXPayOrderQueryRequest.create()
                .setAppid("wx4c0b9bf56ed6df6a").setMch_id("1497389862")
                .setTransaction_id("159534818615249485")
                .setNonce_str(RandomUtils.getRandomNormalString(32))
                .setSign_type(WXPayConstants.HMACSHA256);
        String key = "172b42814464710cf4210d8ca87bf0d6";

        Map<String, Object> result = WXPayApi.orderQuery(wxPayOrderQueryRequest, key);
        result.forEach((k, v) -> System.out.println(k + "===" + v));
    }
}

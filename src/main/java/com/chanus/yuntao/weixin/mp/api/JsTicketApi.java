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
package com.chanus.yuntao.weixin.mp.api;

import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.utils.core.RetryUtils;
import com.chanus.yuntao.utils.core.StringUtils;
import com.chanus.yuntao.utils.core.encrypt.SHAUtils;
import com.chanus.yuntao.weixin.mp.api.bean.JsTicket;
import com.chanus.yuntao.weixin.mp.api.bean.Signature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * JS-SDK API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
 *
 * @author Chanus
 * @date 2020-06-26 11:15:06
 * @since 1.0.0
 */
public class JsTicketApi {
    /**
     * 获取签名凭证 ticket url，请求方式为 GET
     */
    private static final String GET_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=TYPE";

    /**
     * 缓存签名凭证 ticket，Map 的 key 为签名凭证类型，value 为 JsTicket 对象
     */
    private static final Map<String, JsTicket> TICKET_MAP = new HashMap<>();

    /**
     * 获取签名凭证 ticket
     *
     * @param type 签名凭证类型。jsapi: 用于获取公众号调用微信 JS 接口的临时票据；wx_card：用户获取微信卡券接口中使用的签名凭证
     * @return {@link JsTicket}
     */
    public static JsTicket getTicket(String type) {
        JsTicket jsTicket = TICKET_MAP.get(type);
        if (jsTicket != null && jsTicket.isAvailable())
            return jsTicket;

        // 缓存中不存在签名凭证，或是签名凭证已失效
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_TICKET_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        // 最多三次请求
        jsTicket = RetryUtils.retryOnException(3, () -> new JsTicket(HttpUtils.get(url)));
        // 将签名凭证放入缓存
        TICKET_MAP.put(type, jsTicket);

        return jsTicket;
    }

    /**
     * 获取签名凭证 jsapi_ticket
     *
     * @return {@link JsTicket}
     */
    public static JsTicket getJsApiTicket() {
        return getTicket("jsapi");
    }

    /**
     * 获取签名凭证 api_ticket
     *
     * @return {@link JsTicket}
     */
    public static JsTicket getWxCardTicket() {
        return getTicket("wx_card");
    }

    /**
     * 获取签名凭证 jsapi_ticket 字符串
     *
     * @return jsapi_ticket 字符串
     */
    public static String getJsApiTicketStr() {
        return getJsApiTicket().getTicket();
    }

    /**
     * 获取签名凭证 api_ticket 字符串
     *
     * @return api_ticket 字符串
     */
    public static String getWxCardTicketStr() {
        return getWxCardTicket().getTicket();
    }

    /**
     * JS-SDK 使用权限签名生成
     *
     * @param signature 签名参数
     * @return {@link Signature}
     */
    public static Signature signature(Signature signature) {
        String string = "jsapi_ticket=" + signature.getTicket() + "&noncestr=" + signature.getNonceStr() +
                "&timestamp=" + signature.getTimestamp() + "&url=" + signature.getUrl();
        String sign = SHAUtils.sha1(string);

        return signature.setSignature(sign);
    }

    /**
     * 卡券签名生成
     *
     * @param signature 签名参数
     * @return {@link Signature}
     */
    public static Signature cardSign(Signature signature) {
        Set<String> set = new TreeSet<>();
        if (StringUtils.isNotBlank(signature.getOpenId()))
            set.add(signature.getOpenId());
        set.add(signature.getTicket());
        set.add(signature.getCardId());
        set.add(signature.getNonceStr());
        set.add(signature.getTimestamp());
        if (StringUtils.isNotBlank(signature.getCode()))
            set.add(signature.getCode());

        StringBuilder stringBuilder = new StringBuilder();
        set.forEach(stringBuilder::append);

        String sign = SHAUtils.sha1(stringBuilder.toString());

        return signature.setSignature(sign);
    }
}

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
package com.chanus.yuntao.weixin.mp.api.test;

import com.chanus.yuntao.weixin.mp.api.JsTicketApi;
import com.chanus.yuntao.weixin.mp.api.bean.JsTicket;
import com.chanus.yuntao.weixin.mp.api.bean.Signature;
import org.junit.Test;

/**
 * JsTicketApi 测试
 *
 * @author Chanus
 * @date 2020-06-26 13:01:19
 * @since 1.0.0
 */
public class JsTicketApiTest extends WXConfigTest {
    @Test
    public void getTicketTest() {
        JsTicket jsApiTicket = JsTicketApi.getTicket("jsapi");
        System.out.println(jsApiTicket.toString());
        JsTicket wxCardTicket = JsTicketApi.getTicket("wx_card");
        System.out.println(wxCardTicket.toString());
    }

    @Test
    public void getJsApiTicketTest() {
        JsTicket jsTicket = JsTicketApi.getJsApiTicket();
        System.out.println(jsTicket.toString());
    }

    @Test
    public void getWxCardTicketTest() {
        JsTicket jsTicket = JsTicketApi.getWxCardTicket();
        System.out.println(jsTicket.toString());
    }

    @Test
    public void getJsApiTicketStrTest() {
        System.out.println(JsTicketApi.getJsApiTicketStr());
    }

    @Test
    public void getWxCardTicketStrTest() {
        System.out.println(JsTicketApi.getWxCardTicketStr());
    }

    @Test
    public void signatureTest() {
        String nonceStr = "Wm3WZYTPz0wzccnW";
        String ticket = "sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg";
        String timestamp = "1414587457";
        String url = "http://mp.weixin.qq.com?params=value";
        Signature signature = new Signature().setNonceStr(nonceStr).setTicket(ticket)
                .setTimestamp(timestamp).setUrl(url);
        System.out.println(JsTicketApi.signature(signature).toString());// 0f9de62fce790f9a083d5c99e95740ceb90c27ed
    }

    @Test
    public void cardSignTest() {
        // String openId = "openid";
        String openId = null;
        String ticket = "ojZ8YtyVyr30HheH3CM73y7h4jJE";
        String cardId = "pjZ8Yt1XGILfi-FUsewpnnolGgZk";
        String nonceStr = "123";
        String timestamp = "1404896688";
        String code = "1434008071";
        // String code = null;
        Signature signature = new Signature().setOpenId(openId).setTicket(ticket).setCardId(cardId)
                .setNonceStr(nonceStr).setTimestamp(timestamp).setCode(code);
        System.out.println(JsTicketApi.cardSign(signature).toString());
    }
}

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

import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.weixin.mp.api.SnsApi;
import com.chanus.yuntao.weixin.mp.api.bean.SnsAccessToken;
import org.junit.Test;

/**
 * SnsApi 测试
 *
 * @author Chanus
 * @date 2020-05-19 14:42:41
 * @since 1.0.0
 */
public class SnsApiTest extends WXConfigTest {
    @Test
    public void getAuthorizeURLTest() {
        String redirectUri = "http://t.liulianhuan.com/register/oauth2";
        String url = SnsApi.getAuthorizeURL(redirectUri, true);
        System.out.println(url);
        String state = "test";
        url = SnsApi.getAuthorizeURL(redirectUri, false, state);
        System.out.println(url);
    }

    @Test
    public void getQrConnectURLTest() {
        String redirectUri = "http://t.liulianhuan.com/register/oauth2";
        String url = SnsApi.getQrConnectURL(redirectUri);
        System.out.println(url);
        String state = "test";
        url = SnsApi.getQrConnectURL(redirectUri, state);
        System.out.println(url);
    }

    @Test
    public void getSnsAccessTokenTest() {
        String code = "001CH9Bz1TEcJc0tiDAz1RX8Bz1CH9BG";
        SnsAccessToken snsAccessToken = SnsApi.getSnsAccessToken(code);
        System.out.println(snsAccessToken.toString());
    }

    @Test
    public void getUserOpenIdTest() {
        String code = "001CH9Bz1TEcJc0tiDAz1RX8Bz1CH9BG";
        String openId = SnsApi.getUserOpenId(code);
        System.out.println(openId);
    }

    @Test
    public void refreshSnsAccessTokenTest() {
        String refreshToken = "33_9CQql90EruOkAoMeFZmh7jz7EJsQV0u6LSd75jeU1XL4ik7uP-RZkKHObZC1u8z4ysKUx2TBoWGxsQwa-LXFOhrVhGuLZPTicDicbJJWyT8";
        SnsAccessToken snsAccessToken = SnsApi.refreshSnsAccessToken(refreshToken);
        System.out.println(snsAccessToken.toString());
    }

    @Test
    public void getUserInfoTest() {
        String accessToken = "33_Cog0JM9pFMfzIetwrQJlDkJcKnXPQlDfk2eoMxZDoze5FREB45EGp8THp_sFj3l-a9dEfXbhU1FA5jgySCcKPNmno4crqSmCLZlEDTj7SVM";
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = SnsApi.getUserInfo(accessToken, openId);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void authSnsAccessTokenTest() {
        String accessToken = "33_Cog0JM9pFMfzIetwrQJlDkJcKnXPQlDfk2eoMxZDoze5FREB45EGp8THp_sFj3l-a9dEfXbhU1FA5jgySCcKPNmno4crqSmCLZlEDTj7SVM";
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = SnsApi.authSnsAccessToken(accessToken, openId);
        System.out.println(jsonObject.toJSONString());
    }
}

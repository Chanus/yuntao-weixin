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
import com.chanus.yuntao.weixin.mp.api.bean.AccessToken;
import com.chanus.yuntao.weixin.mp.api.bean.WXConfig;

/**
 * 认证并获取 access_token API<br>
 * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
 *
 * @author Chanus
 * @date 2020-05-15 21:04:02
 * @since 1.0.0
 */
public class AccessTokenApi {
    /**
     * 获取 access_token url，请求方式为 GET
     */
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 公众号开发信息
     */
    public static WXConfig wxConfig = null;

    /**
     * 用于手动设置的 accessToken
     */
    private static AccessToken accessToken = null;

    /**
     * 移除 access_token
     */
    public static void removeAccessToken() {
        accessToken = null;
    }

    /**
     * 从缓存中获取 access_token，如果未取到或者 access_token 不可用则先更新再获取
     *
     * @return {@code AccessToken}
     */
    public static AccessToken getAccessToken() {
        if (accessToken != null && accessToken.isAvailable())
            return accessToken;

        String url = TOKEN_URL.replace("APPID", wxConfig.getAppId()).replace("APPSECRET", wxConfig.getAppSecret());
        // 发起 GET 请求获取凭证
        String result = HttpUtils.get(url);
        AccessToken token = null;
        if (StringUtils.isNotBlank(result)) {
            token = new AccessToken(result);
        }
        if (null == token || !token.isAvailable())
            token = refreshAccessToken();

        return token;
    }

    /**
     * 获取 access_token 字符串
     *
     * @return 获取到的凭证
     */
    public static String getAccessTokenStr() {
        AccessToken accessToken = getAccessToken();
        if (StringUtils.isBlank(accessToken.getAccessToken()))
            throw new RuntimeException(accessToken.getErrMsg());

        return getAccessToken().getAccessToken();
    }

    /**
     * 无条件强制更新 access_token 值，不再对 cache 中的 token 进行判断
     *
     * @return {@code AccessToken}
     */
    public static AccessToken refreshAccessToken() {
        String url = TOKEN_URL.replace("APPID", wxConfig.getAppId()).replace("APPSECRET", wxConfig.getAppSecret());
        // 最多三次请求
        return RetryUtils.retryOnException(3, () -> {
            String json = HttpUtils.get(url);
            return new AccessToken(json);
        });
    }
}

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
package com.chanus.yuntao.weixin.mp.api.bean;

import java.io.Serializable;

/**
 * 公众号开发信息配置<br>
 * 详情请见: https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
 *
 * @author Chanus
 * @date 2020-05-17 13:55:36
 * @since 1.0.0
 */
public class WXConfig implements Serializable {
    private static final long serialVersionUID = -7251374840133877967L;

    /**
     * 开发者ID
     */
    private String appId;
    /**
     * 开发者密码
     */
    private String appSecret;
    /**
     * 令牌
     */
    private String token;
    /**
     * 消息加解密密钥
     */
    private String encodingAESKey;
    /**
     * 消息加解密方式，{@code false}表示明文模式，{@code true}表示安全模式，默认为明文模式
     */
    private boolean messageEncrypt = false;

    public WXConfig() {
    }

    public WXConfig(String token) {
        setToken(token);
    }

    public WXConfig(String appId, String appSecret) {
        setAppId(appId);
        setAppSecret(appSecret);
    }

    public WXConfig(String appId, String appSecret, String token) {
        setAppId(appId);
        setAppSecret(appSecret);
        setToken(token);
    }

    public WXConfig(String appId, String appSecret, String token, String encodingAESKey, boolean messageEncrypt) {
        setAppId(appId);
        setAppSecret(appSecret);
        setToken(token);
        setEncodingAESKey(encodingAESKey);
        setMessageEncrypt(messageEncrypt);
    }

    public String getAppId() {
        if (appId == null)
            throw new IllegalStateException("appId 未被赋值");
        return appId;
    }

    public void setAppId(String appId) {
        if (appId == null)
            throw new IllegalStateException("appId 值不能为 null");
        this.appId = appId;
    }

    public String getAppSecret() {
        if (appSecret == null)
            throw new IllegalStateException("appSecret 未被赋值");
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        if (appSecret == null)
            throw new IllegalStateException("appSecret 值不能为 null");
        this.appSecret = appSecret;
    }

    public String getToken() {
        if (token == null)
            throw new IllegalStateException("token 未被赋值");
        return token;
    }

    public void setToken(String token) {
        if (token == null)
            throw new IllegalStateException("token 值不能为 null");
        this.token = token;
    }

    public String getEncodingAESKey() {
        if (encodingAESKey == null)
            throw new IllegalStateException("encodingAESKey 未被赋值");
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        if (encodingAESKey == null)
            throw new IllegalStateException("encodingAESKey 值不能为 null");
        this.encodingAESKey = encodingAESKey;
    }

    public boolean isMessageEncrypt() {
        return messageEncrypt;
    }

    public void setMessageEncrypt(boolean messageEncrypt) {
        this.messageEncrypt = messageEncrypt;
    }
}

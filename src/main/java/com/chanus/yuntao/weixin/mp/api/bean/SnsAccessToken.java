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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.RetryUtils;

import java.io.Serializable;

/**
 * 封装网页授权 access_token
 *
 * @author Chanus
 * @date 2020-05-19 15:28:11
 * @since 1.0.0
 */
public class SnsAccessToken implements RetryUtils.ResultCheck, Serializable {
    private static final long serialVersionUID = -47064589821527845L;

    /**
     * 网页授权接口调用凭证，注意：此 access_token 与基础支持的 access_token 不同
     */
    private String access_token;
    /**
     * access_token 接口调用凭证超时时间，单位（秒）
     */
    private Integer expires_in;
    /**
     * 用户刷新 access_token
     */
    private String refresh_token;
    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的 OpenID
     */
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
    /**
     *
     */
    private String unionid;
    /**
     * 错误代码，出错时有值
     */
    private Integer errcode;
    /**
     * 错误信息，出错时有值
     */
    private String errmsg;
    /**
     * 正确获取到 access_token 时有值，存放过期时间
     */
    private Long expiredTime;

    private String json;

    public SnsAccessToken() {
    }

    /**
     * 构造方法
     *
     * @param json 请求微信接口获取 access_token 时返回的数据
     */
    public SnsAccessToken(String json) {
        this.json = json;

        try {
            JSONObject jsonObject = JSON.parseObject(json);
            this.access_token = jsonObject.getString("access_token");
            this.expires_in = jsonObject.getInteger("expires_in");
            this.refresh_token = jsonObject.getString("refresh_token");
            this.openid = jsonObject.getString("openid");
            this.scope = jsonObject.getString("scope");
            this.unionid = jsonObject.getString("unionid");
            this.errcode = jsonObject.getInteger("errcode");
            this.errmsg = jsonObject.getString("errmsg");

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证 access_token 是否有效
     *
     * @return 返回 {@code true} access_token 有效，返回 {@code false} access_token 无效
     */
    public boolean isAvailable() {
        if (errcode != null || expiredTime == null || expiredTime < System.currentTimeMillis())
            return false;
        return access_token != null;
    }

    public String getAccessToken() {
        return access_token;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public String getScope() {
        return scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public Integer getErrCode() {
        return errcode;
    }

    public String getErrMsg() {
        if (errcode != null) {
            String result = ErrorMsg.get(errcode);
            if (result != null)
                return result;
        }
        return errmsg;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    @Override
    public boolean matching() {
        return isAvailable();
    }

    @Override
    public String getJson() {
        return json;
    }

    @Override
    public String toString() {
        return "SnsAccessToken{" +
                "access_token=" + access_token +
                ", expires_in=" + expires_in +
                ", refresh_token=" + refresh_token +
                ", openid=" + openid +
                ", scope=" + scope +
                ", unionid=" + unionid +
                ", errcode=" + errcode +
                ", errmsg=" + errmsg +
                ", expiredTime=" + expiredTime +
                ", json=" + json +
                '}';
    }
}

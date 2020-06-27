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
 * 封装 js ticket
 *
 * @author Chanus
 * @date 2020-06-26 11:41:29
 * @since 1.0.0
 */
public class JsTicket implements RetryUtils.ResultCheck, Serializable {
    private static final long serialVersionUID = -2429995606444260391L;

    /**
     * 获取到的凭证，正确获取到 ticket 时有值
     */
    private String ticket;
    /**
     * 凭证有效时间，单位：秒，正确获取到 ticket 时有值
     */
    private Integer expires_in;
    /**
     * 错误代码，出错时有值
     */
    private Integer errcode;
    /**
     * 错误信息，出错时有值
     */
    private String errmsg;

    /**
     * 正确获取到 ticket 时有值，存放过期时间
     */
    private Long expiredTime;

    private String json;

    public JsTicket() {
    }

    /**
     * 构造方法
     *
     * @param json 请求微信接口获取 ticket 时返回的数据
     */
    public JsTicket(String json) {
        this.json = json;
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            this.ticket = jsonObject.getString("ticket");
            this.expires_in = jsonObject.getInteger("expires_in");
            this.errcode = jsonObject.getInteger("errcode");
            this.errmsg = jsonObject.getString("errmsg");

            if (expires_in != null) {
                // expires_in - 9  用于控制在 ticket 过期之前 9 秒就 "主动" 再次获取 ticket
                // 避免大并发场景下多线程同时获取 ticket，造成公众平台 api 调用额度的浪费
                expiredTime = System.currentTimeMillis() + ((expires_in - 9) * 1000);
            }

            // 用户缓存时还原
            if (jsonObject.containsKey("expiredTime")) {
                expiredTime = jsonObject.getLong("expiredTime");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCacheJson() {
        JSONObject jsonObject = JSON.parseObject(json);
        jsonObject.put("expiredTime", expiredTime);
        jsonObject.remove("expires_in");
        return jsonObject.toJSONString();
    }

    /**
     * 验证 ticket 是否有效
     *
     * @return 返回{@code true} ticket 有效，返回 {@code false} ticket 无效
     */
    public boolean isAvailable() {
        if (expiredTime == null)
            return false;
        if (errcode != null && errcode != 0)
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;

        return ticket != null;
    }

    @Override
    public boolean matching() {
        return isAvailable();
    }

    @Override
    public String getJson() {
        return json;
    }

    public String getTicket() {
        return ticket;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
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
    public String toString() {
        return "JsTicket{" +
                "ticket=" + ticket +
                ", expires_in=" + expires_in +
                ", errcode=" + errcode +
                ", errmsg=" + errmsg +
                ", expiredTime=" + expiredTime +
                ", json=" + json +
                '}';
    }
}

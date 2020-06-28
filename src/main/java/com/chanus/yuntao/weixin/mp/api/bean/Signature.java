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
 * JS-SDK 签名封装
 *
 * @author Chanus
 * @date 2020-06-28 15:33:30
 * @since 1.0.0
 */
public class Signature implements Serializable {
    private static final long serialVersionUID = 4556005732033312700L;

    /**
     * 签名凭证
     */
    private String ticket;
    /**
     * 随机字符串，由开发者设置传入，加强安全性（若不填写可能被重放请求）。随机字符串，不长于32位。推荐使用大小写字母和数字，不同添加请求的 nonce 须动态生成，若重复将会导致领取失败
     */
    private String nonceStr;
    /**
     * 时间戳，商户生成从1970年1月1日00:00:00至今的秒数，即当前的时间，且最终需要转换为字符串形式；由商户生成后传入，不同添加请求的时间戳须动态生成，若重复将会导致领取失败
     */
    private String timestamp;
    /**
     * 调用 JS 接口页面的完整 URL，不包含#及其后面部分。用于 JS-SDK 使用权限签名
     */
    private String url;
    /**
     * 指定领取者的 openid，只有该用户能领取。bind_openid 字段为 true 的卡券必须填写，bind_openid 字段为 false 不必填写。用于卡券签名
     */
    private String openId;
    /**
     * 卡券 ID。用于卡券签名
     */
    private String cardId;
    /**
     * 指定的卡券 code 码，只能被领一次。自定义 code 模式的卡券必须填写，非自定义 code 和预存 code 模式的卡券不必填写。用于卡券签名
     */
    private String code;
    /**
     * sha1 算法生成的签名
     */
    private String signature;

    public String getTicket() {
        return ticket;
    }

    public Signature setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public Signature setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Signature setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Signature setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public Signature setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public Signature setCardId(String cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Signature setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public Signature setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "ticket=" + ticket +
                ", nonceStr=" + nonceStr +
                ", timestamp=" + timestamp +
                ", url=" + url +
                ", openId=" + openId +
                ", cardId=" + cardId +
                ", code=" + code +
                ", signature=" + signature +
                '}';
    }
}

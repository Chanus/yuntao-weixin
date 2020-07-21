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
package com.chanus.yuntao.weixin.pay.request;

import com.chanus.yuntao.utils.core.ObjectUtils;
import com.chanus.yuntao.weixin.utils.WXPayUtils;

import java.util.Map;

/**
 * 订单查询请求参数
 *
 * @author Chanus
 * @date 2020-07-21 22:39:41
 * @since 1.1.0
 */
public class WXPayOrderQueryRequest {
    /**
     * 公众账号 ID。微信支付分配的公众账号 ID（企业号 corpid 即为此 appId）
     */
    private String appid;
    /**
     * 商户号。微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 微信订单号。微信的订单号，建议优先使用
     */
    private String transaction_id;
    /**
     * 商户订单号。商户系统内部订单号，要求32个字符内，只能是数字、大小写字母 _-|*@，且在同一个商户号下唯一
     */
    private String out_trade_no;
    /**
     * 随机字符串。不长于32位
     */
    private String nonce_str;
    /**
     * 签名类型。目前支持 HMAC-SHA256 和 MD5，默认为MD5
     */
    private String sign_type;

    /**
     * 创建一个 WXPayOrderQueryRequest 的对象实例
     *
     * @return WXPayOrderQueryRequest 的对象实例
     */
    public static WXPayOrderQueryRequest create() {
        return new WXPayOrderQueryRequest();
    }

    /**
     * 将当前对象转化成 Map
     *
     * @return Map
     */
    public Map<String, Object> toMap() {
        return ObjectUtils.toMap(this);
    }

    /**
     * 将当前对象转化成 XML 字符串
     *
     * @return XML 字符串
     */
    public String toXml() {
        return WXPayUtils.mapToXml(this.toMap());
    }

    /**
     * 将当前对象转化成带有 sign 的 XML 格式字符串
     *
     * @param key API 密钥
     * @return XML 字符串
     */
    public String toSignedXml(String key) {
        return WXPayUtils.generateSignedXml(this.toMap(), key, this.getSign_type());
    }

    public String getAppid() {
        return appid;
    }

    public WXPayOrderQueryRequest setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public WXPayOrderQueryRequest setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public WXPayOrderQueryRequest setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public WXPayOrderQueryRequest setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public WXPayOrderQueryRequest setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign_type() {
        return sign_type;
    }

    public WXPayOrderQueryRequest setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    @Override
    public String toString() {
        return "WXPayOrderQueryRequest{" +
                "appid=" + appid +
                ", mch_id=" + mch_id +
                ", transaction_id=" + transaction_id +
                ", out_trade_no=" + out_trade_no +
                ", nonce_str=" + nonce_str +
                ", sign_type=" + sign_type +
                '}';
    }
}

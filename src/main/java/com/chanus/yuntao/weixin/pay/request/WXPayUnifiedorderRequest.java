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

import com.alibaba.fastjson.JSON;
import com.chanus.yuntao.weixin.utils.WXPayUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一下单请求参数
 *
 * @author Chanus
 * @date 2020-07-19 15:45:47
 * @since 1.1.0
 */
public class WXPayUnifiedorderRequest {
    /**
     * 公众账号 ID。微信支付分配的公众账号 ID（企业号 corpid 即为此 appId）
     */
    private String appid;
    /**
     * 商户号。微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 设备号。自定义参数，可以为终端设备号（门店号或收银设备ID），PC 网页或公众号内支付可以传"WEB"
     */
    private String device_info;
    /**
     * 随机字符串。随机字符串，长度要求在32位以内
     */
    private String nonce_str;
    /**
     * 签名类型。签名类型，默认为 MD5，支持 HMAC-SHA256 和 MD5
     */
    private String sign_type;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据。在查询API和支付通知中原样返回，可作为自定义参数使用
     */
    private String attach;
    /**
     * 商户订单号。商户系统内部订单号，要求32个字符内，只能是数字、大小写字母 _-|* 且在同一个商户号下唯一
     */
    private String out_trade_no;
    /**
     * 标价币种。符合 ISO 4217 标准的三位字母代码，默认人民币：CNY
     */
    private String fee_type;
    /**
     * 标价金额。订单总金额，单位为分
     */
    private String total_fee;
    /**
     * 终端IP。支持IPV4和IPV6两种格式的IP地址，用户的客户端IP
     */
    private String spbill_create_ip;
    /**
     * 交易起始时间。订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String time_start;
    /**
     * 交易结束时间。订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，
     * 由于在请求支付的时候有一个必传参数 prepay_id 只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的 prepay_id。
     * time_expire 只能第一次下单传值，不允许二次修改，二次修改系统将报错。如用户支付失败后，需再次支付，需更换原订单号重新下单。
     * 建议：最短失效时间间隔大于1分钟
     */
    private String time_expire;
    /**
     * 订单优惠标记。使用代金券或立减优惠功能时需要的参数
     */
    private String goods_tag;
    /**
     * 通知地址，异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     */
    private String notify_url;
    /**
     * 交易类型。JSAPI - JSAPI支付，NATIVE - Native支付，APP - APP支付
     */
    private String trade_type;
    /**
     * 商品ID。trade_type=NATIVE 时，此参数必传。此参数为二维码中包含的商品 ID，商户自行定义
     */
    private String product_id;
    /**
     * 指定支付方式。上传此参数 no_credit--可限制用户不能使用信用卡支付
     */
    private String limit_pay;
    /**
     * 用户标识。trade_type=JSAPI 时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应 appid 下的唯一标识
     */
    private String openid;
    /**
     * 电子发票入口开放标识。Y，传入 Y 时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    private String receipt;
    /**
     * 场景信息。该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为 JSON 对象数据
     */
    private String scene_info;

    /**
     * 创建一个 WXPayUnifiedorderRequest 的对象实例
     *
     * @return WXPayUnifiedorderRequest 的对象实例
     */
    public static WXPayUnifiedorderRequest create() {
        return new WXPayUnifiedorderRequest();
    }

    /**
     * 将当前对象转化成 Map
     *
     * @return Map
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        Object obj;
        for (Field field : fields) {
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj.toString());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
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

    public WXPayUnifiedorderRequest setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public WXPayUnifiedorderRequest setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public WXPayUnifiedorderRequest setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public WXPayUnifiedorderRequest setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign_type() {
        return sign_type;
    }

    public WXPayUnifiedorderRequest setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public String getBody() {
        return body;
    }

    public WXPayUnifiedorderRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public WXPayUnifiedorderRequest setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public WXPayUnifiedorderRequest setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public WXPayUnifiedorderRequest setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getFee_type() {
        return fee_type;
    }

    public WXPayUnifiedorderRequest setFee_type(String fee_type) {
        this.fee_type = fee_type;
        return this;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public WXPayUnifiedorderRequest setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public WXPayUnifiedorderRequest setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
        return this;
    }

    public String getTime_start() {
        return time_start;
    }

    public WXPayUnifiedorderRequest setTime_start(String time_start) {
        this.time_start = time_start;
        return this;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public WXPayUnifiedorderRequest setTime_expire(String time_expire) {
        this.time_expire = time_expire;
        return this;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public WXPayUnifiedorderRequest setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
        return this;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public WXPayUnifiedorderRequest setNotify_url(String notify_url) {
        this.notify_url = notify_url;
        return this;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public WXPayUnifiedorderRequest setTrade_type(String trade_type) {
        this.trade_type = trade_type;
        return this;
    }

    public String getProduct_id() {
        return product_id;
    }

    public WXPayUnifiedorderRequest setProduct_id(String product_id) {
        this.product_id = product_id;
        return this;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public WXPayUnifiedorderRequest setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public WXPayUnifiedorderRequest setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getReceipt() {
        return receipt;
    }

    public WXPayUnifiedorderRequest setReceipt(String receipt) {
        this.receipt = receipt;
        return this;
    }

    public String getScene_info() {
        return scene_info;
    }

    public WXPayUnifiedorderRequest setScene_info(String scene_info) {
        this.scene_info = scene_info;
        return this;
    }

    public WXPayUnifiedorderRequest setScene_info(SceneInfo scene_info) {
        this.scene_info = JSON.toJSONString(scene_info);
        return this;
    }

    @Override
    public String toString() {
        return "WXPayUnifiedorderRequest{" +
                "appid=" + appid +
                ", mch_id=" + mch_id +
                ", device_info=" + device_info +
                ", nonce_str=" + nonce_str +
                ", sign_type=" + sign_type +
                ", body=" + body +
                ", detail=" + detail +
                ", attach=" + attach +
                ", out_trade_no=" + out_trade_no +
                ", fee_type=" + fee_type +
                ", total_fee=" + total_fee +
                ", spbill_create_ip=" + spbill_create_ip +
                ", time_start=" + time_start +
                ", time_expire=" + time_expire +
                ", goods_tag=" + goods_tag +
                ", notify_url=" + notify_url +
                ", trade_type=" + trade_type +
                ", product_id=" + product_id +
                ", limit_pay=" + limit_pay +
                ", openid=" + openid +
                ", receipt=" + receipt +
                ", scene_info=" + scene_info +
                '}';
    }

    public static class SceneInfo {
        /**
         * 门店id。门店编号，由商户自定义
         */
        private String id;
        /**
         * 门店名称。由商户自定义
         */
        private String name;
        /**
         * 门店行政区划码
         */
        private String area_code;
        /**
         * 门店详细地址
         */
        private String address;

        public SceneInfo() {
        }

        public SceneInfo(String id, String name, String area_code, String address) {
            this.id = id;
            this.name = name;
            this.area_code = area_code;
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public SceneInfo setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public SceneInfo setName(String name) {
            this.name = name;
            return this;
        }

        public String getArea_code() {
            return area_code;
        }

        public SceneInfo setArea_code(String area_code) {
            this.area_code = area_code;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public SceneInfo setAddress(String address) {
            this.address = address;
            return this;
        }
    }
}

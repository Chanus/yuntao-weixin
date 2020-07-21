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

/**
 * 统一下单请求参数
 *
 * @author Chanus
 * @date 2020-07-19 15:45:47
 * @since 1.1.0
 */
public class WxPayUnifiedorderRequest {
    /**
     * 公众账号 ID，微信支付分配的公众账号 ID（企业号 corpid 即为此 appId）
     */
    private String appid;
    /**
     *
     */
    private String mch_id;
    /**
     *
     */
    private String device_info;
    /**
     *
     */
    private String nonce_str;
    /**
     *
     */
    private String sign;
    /**
     *
     */
    private String sign_type;
    /**
     *
     */
    private String body;
    /**
     *
     */
    private String detail;
    /**
     *
     */
    private String attach;
    /**
     *
     */
    private String out_trade_no;
    /**
     *
     */
    private String fee_type;
    /**
     *
     */
    private String total_fee;
    /**
     *
     */
    private String spbill_create_ip;
    /**
     *
     */
    private String time_start;
    /**
     *
     */
    private String time_expire;
    /**
     *
     */
    private String goods_tag;
    /**
     *
     */
    private String notify_url;
    /**
     *
     */
    private String trade_type;
    /**
     *
     */
    private String product_id;
}

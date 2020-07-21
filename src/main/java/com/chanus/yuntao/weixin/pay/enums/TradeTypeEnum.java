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
package com.chanus.yuntao.weixin.pay.enums;

/**
 * 交易类型
 *
 * @author Chanus
 * @date 2020-07-21 10:06:36
 * @since 1.1.0
 */
public enum TradeTypeEnum {
    /**
     * JSAPI 支付（或小程序支付）
     */
    JSAPI,
    /**
     * H5 支付
     */
    MWEB,
    /**
     * Native 支付
     */
    NATIVE,
    /**
     * app 支付
     */
    APP
}

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
package com.chanus.yuntao.weixin.pay;

import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.weixin.pay.constants.WXPayConstants;
import com.chanus.yuntao.weixin.pay.request.WXPayUnifiedorderRequest;
import com.chanus.yuntao.weixin.utils.WXPayUtils;

import java.util.Map;

/**
 * 微信支付 API<br>
 * 详情请见：https://pay.weixin.qq.com/wiki/doc/api/index.html
 *
 * @author Chanus
 * @date 2020-07-21 09:27:59
 * @since 1.1.0
 */
public class WXPayApi {
    /**
     * 统一下单
     *
     * @param wxPayUnifiedorderRequest 请求参数
     * @param key                      API 密钥
     * @return 请求返回结果
     */
    public static Map<String, String> unifiedOrder(WXPayUnifiedorderRequest wxPayUnifiedorderRequest, String key) {
        String result = HttpUtils.post(WXPayConstants.DOMAIN_API + WXPayConstants.UNIFIEDORDER_URL_SUFFIX, wxPayUnifiedorderRequest.toSignedXml(key));
        return WXPayUtils.xmlToMap(result);
    }
}

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.utils.core.HttpUtils;

/**
 * 获取微信服务器IP地址 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_the_WeChat_server_IP_address.html
 *
 * @author Chanus
 * @date 2020-05-18 15:35:52
 * @since 1.0.0
 */
public class CallbackIpApi {
    /**
     * 获取微信 callback IP 地址 url，请求方式为 GET
     */
    private static final String GET_CALLBACK_IP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
    /**
     * 获取微信 API 接口 IP 地址 url，请求方式为 GET
     */
    private static final String GET_API_IP_URL = "https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN";

    /**
     * 获取设置的行业信息
     *
     * @return 请求结果的 json 对象
     */
    public static JSONObject getCallBackIp() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_CALLBACK_IP_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取设置的行业信息
     *
     * @return 请求结果的 json 对象
     */
    public static JSONObject getApiIp() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_API_IP_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

}

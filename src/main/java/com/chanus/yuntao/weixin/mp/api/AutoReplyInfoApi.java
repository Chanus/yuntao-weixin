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
 * 获取自动回复规则 API<br>
 * 详情请见：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Getting_Rules_for_Auto_Replies.html
 *
 * @author Chanus
 * @date 2020-05-21 23:15:27
 * @since 1.0.0
 */
public class AutoReplyInfoApi {
    /**
     * 获取自动回复规则 url，请求方式为 GET
     */
    private static final String GET_CURRENT_AUTOREPLY_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN";

    /**
     * 获取自动回复规则
     *
     * @return 请求结果的 json 对象
     */
    public static JSONObject getCurrentAutoreplyInfo() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_CURRENT_AUTOREPLY_INFO_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }
}

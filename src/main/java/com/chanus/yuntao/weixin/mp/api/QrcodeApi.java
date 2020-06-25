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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成带参数的二维码 API<br>
 * 详情请见:
 * <pre>
 *     https://developers.weixin.qq.com/doc/offiaccount/Account_Management/Generating_a_Parametric_QR_Code.html
 *     https://developers.weixin.qq.com/doc/offiaccount/Account_Management/URL_Shortener.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-05-18 00:07:34
 * @since 1.0.0
 */
public class QrcodeApi {
    /**
     * 创建二维码 ticket url，请求方式为 POST
     */
    private static final String QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    /**
     * 通过 ticket 换取二维码 url，请求方式为 GET，TICKET 需要进行 UrlEncode
     */
    private static final String SHOW_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    /**
     * 长链接转短链接接口 url，请求方式为 POST
     */
    private static final String SHORTURL_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

    /**
     * 创建二维码 ticket
     *
     * @param params POST 数据
     * @return 二维码信息的 json 对象
     */
    private static JSONObject create(String params) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = QRCODE_URL.replace("TOKEN", accessToken);
        String result = HttpUtils.post(url, params);

        return JSON.parseObject(result);
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为60秒
     * @param sceneId       场景值 ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return 二维码信息的 json 对象
     */
    public static JSONObject createTemporary(int expireSeconds, int sceneId) {
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_id", sceneId);
        actionInfo.put("scene", scene);

        params.put("action_info", actionInfo);

        return create(JSON.toJSONString(params));
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为60秒
     * @param sceneStr      场景值 ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return 二维码信息的 json 对象
     */
    public static JSONObject createTemporary(int expireSeconds, String sceneStr) {
        Map<String, Object> params = new HashMap<>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_STR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_str", sceneStr);
        actionInfo.put("scene", scene);

        params.put("action_info", actionInfo);

        return create(JSON.toJSONString(params));
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值 ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return 二维码信息的 json 对象
     */
    public static JSONObject createPermanent(int sceneId) {
        Map<String, Object> params = new HashMap<>();
        params.put("action_name", "QR_LIMIT_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_id", sceneId);
        actionInfo.put("scene", scene);

        params.put("action_info", actionInfo);

        return create(JSON.toJSONString(params));
    }

    /**
     * 创建永久二维码
     *
     * @param sceneStr 场景值 ID（字符串形式的 ID），字符串类型，长度限制为1到64
     * @return 二维码信息的 json 对象
     */
    public static JSONObject createPermanent(String sceneStr) {
        Map<String, Object> params = new HashMap<>();
        params.put("action_name", "QR_LIMIT_STR_SCENE");

        Map<String, Object> actionInfo = new HashMap<>();
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene_str", sceneStr);
        actionInfo.put("scene", scene);

        params.put("action_info", actionInfo);

        return create(JSON.toJSONString(params));
    }

    /**
     * 通过 ticket 换取二维码地址
     *
     * @param ticket 换取二维码参数
     * @return 带参数的二维码地址
     */
    public static String getShowQrcodeUrl(String ticket) {
        try {
            return SHOW_QRCODE_URL.replace("TICKET", URLEncoder.encode(ticket, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 需要转换的长链接，支持 http://、https://、weixin://wxpay 格式的 url
     * @return 短连接信息
     */
    public static JSONObject getShortUrl(String longUrl) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = SHORTURL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "long2short");
        jsonObject.put("long_url", longUrl);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 需要转换的长链接，支持 http://、https://、weixin://wxpay 格式的 url
     * @return 请求成功返回短连接 url，否则返回空
     */
    public static String getShortUrlStr(String longUrl) {
        try {
            JSONObject jsonObject = getShortUrl(longUrl);
            if (jsonObject.getIntValue("errcode") == 0)
                return jsonObject.getString("short_url");
        } catch (Exception e) {
            throw new RuntimeException("长链接转短链接接口请求异常", e);
        }
        return null;
    }
}

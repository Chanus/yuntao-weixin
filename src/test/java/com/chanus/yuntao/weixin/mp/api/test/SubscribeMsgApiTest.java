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
package com.chanus.yuntao.weixin.mp.api.test;

import com.alibaba.fastjson.JSONObject;
import com.chanus.yuntao.weixin.mp.api.SubscribeMsgApi;
import com.chanus.yuntao.weixin.mp.api.bean.TemplateMessage;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * SubscribeMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-27 13:15:38
 * @since 1.0.0
 */
public class SubscribeMsgApiTest extends WXConfigTest {
    @Test
    public void getAuthorizeURLTest() throws UnsupportedEncodingException {
        String url = SubscribeMsgApi.getAuthorizeURL(1000, "rFpUW9knKNbu1by6EYd80OnfqNGhMGrr4JLAtjfMnPA",
                "http://t.liulianhuan.com/teacher/register?schoolCode=350524001", "test");

        System.out.println(url);
    }

    @Test
    public void subscribeTest() {
        String json = TemplateMessage.create().setTouser("o9Q3g0c-95M7vLPzPY4iUfLyCLVs")
                .setTemplate_id("rFpUW9knKNbu1by6EYd80OnfqNGhMGrr4JLAtjfMnPA")
                .setUrl("http://t.liulianhuan.com/teacher/register?schoolCode=350524001")
                .setScene("1000").setTitle("一次性订阅消息")
                .setSubscribeData("一次性订阅消息")
                .toJSONString();

        System.out.println(json);

        JSONObject jsonObject = SubscribeMsgApi.subscribe(json);
        System.out.println(jsonObject.toJSONString());
    }
}

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
import com.chanus.yuntao.weixin.mp.api.NewsCommentApi;
import org.junit.Test;

/**
 * NewsCommentApi 测试
 *
 * @author Chanus
 * @date 2020-06-07 17:15:12
 * @since 1.0.0
 */
public class NewsCommentApiTest extends WXConfigTest {
    @Test
    public void openTest() {
        JSONObject jsonObject = NewsCommentApi.open(2247483669L, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void closeTest() {
        JSONObject jsonObject = NewsCommentApi.close(2247483669L, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void listTest() {
        JSONObject jsonObject = NewsCommentApi.list(2247483669L, null, 0, 20, 0);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void markElectTest() {
        JSONObject jsonObject = NewsCommentApi.markElect(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void unmarkElectTest() {
        JSONObject jsonObject = NewsCommentApi.unmarkElect(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteTest() {
        JSONObject jsonObject = NewsCommentApi.delete(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void replyTest() {
        JSONObject jsonObject = NewsCommentApi.reply(2247483669L, null, 1, "很好");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteReplyTest() {
        JSONObject jsonObject = NewsCommentApi.deleteReply(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }
}

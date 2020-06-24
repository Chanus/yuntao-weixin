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
import com.chanus.yuntao.weixin.mp.api.BlackUserApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackUserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:16:24
 * @since 1.0.0
 */
public class BlackUserApiTest extends WXConfigTest {
    @Test
    public void getBlackListTest() {
        JSONObject jsonObject1 = BlackUserApi.getBlackList();
        System.out.println(jsonObject1.toJSONString());

        String beginOpenid = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject2 = BlackUserApi.getBlackList(beginOpenid);
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchBlackUsersTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");

        JSONObject jsonObject = BlackUserApi.batchBlackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchUnblackUsersTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");

        JSONObject jsonObject = BlackUserApi.batchUnblackUsers(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}

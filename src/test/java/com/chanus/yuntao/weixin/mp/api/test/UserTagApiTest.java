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
import com.chanus.yuntao.weixin.mp.api.UserTagApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * UserTagApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:15:50
 * @since 1.0.0
 */
public class UserTagApiTest extends WXConfigTest {
    @Test
    public void createTest() {
        JSONObject jsonObject = UserTagApi.create("福建");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTest() {
        JSONObject jsonObject = UserTagApi.get();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void updateTest() {
        JSONObject jsonObject = UserTagApi.update(101, "安徽");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteTest() {
        JSONObject jsonObject = UserTagApi.delete(100);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTagUsersTest() {
        JSONObject jsonObject = UserTagApi.getTagUsers(100, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchTaggingTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserTagApi.batchTagging(104, openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void batchUntaggingTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserTagApi.batchUntagging(100, openIdList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserTagsTest() {
        JSONObject jsonObject = UserTagApi.getUserTags("o-mAK56WJbFNVxrptEbo80apufVc");
        System.out.println(jsonObject.toJSONString());
    }
}

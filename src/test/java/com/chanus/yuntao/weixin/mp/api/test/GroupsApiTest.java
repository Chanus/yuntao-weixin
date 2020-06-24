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
import com.chanus.yuntao.weixin.mp.api.GroupsApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * GroupsApi 测试
 *
 * @author Chanus
 * @date 2020-05-21 22:42:41
 * @since 1.0.0
 */
public class GroupsApiTest extends WXConfigTest {
    @Test
    public void createTest() {
        JSONObject jsonObject = GroupsApi.create("安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void getTest() {
        JSONObject jsonObject = GroupsApi.get();
        System.out.println(jsonObject);
    }

    @Test
    public void getUserGroupIdTest() {
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = GroupsApi.getUserGroupId(openId);
        System.out.println(jsonObject);
    }

    @Test
    public void updateTest() {
        JSONObject jsonObject = GroupsApi.update(103, "安徽");
        System.out.println(jsonObject);
    }

    @Test
    public void updateUserGroupsTest() {
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = GroupsApi.updateUserGroups(openId, 103);
        System.out.println(jsonObject);
    }

    @Test
    public void batchUpdateUserGroupsTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = GroupsApi.batchUpdateUserGroups(openIdList, 102);
        System.out.println(jsonObject);
    }

    @Test
    public void deleteTest() {
        JSONObject jsonObject = GroupsApi.delete(103);
        System.out.println(jsonObject);
    }
}

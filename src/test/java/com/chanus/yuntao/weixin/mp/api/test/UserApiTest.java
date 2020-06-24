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
import com.chanus.yuntao.weixin.mp.api.UserApi;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * UserApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 16:05:54
 * @since 1.0.0
 */
public class UserApiTest extends WXConfigTest {
    @Test
    public void updateRemarkTest() {
        JSONObject jsonObject = UserApi.updateRemark("o9Q3g0c-95M7vLPzPY4iUfLyCLVs", "小胖");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserInfoTest() {
        JSONObject jsonObject = UserApi.getUserInfo("oThyXtzi1FqPfKfM4MQYX51_c9ng");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUsersTest() {
        JSONObject jsonObject1 = UserApi.getUsers("oThyXtzi1FqPfKfM4MQYX51_c9ng");
        System.out.println(jsonObject1.toJSONString());
        JSONObject jsonObject2 = UserApi.getUsers();
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void batchGetUserInfoTest() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("o9Q3g0c-95M7vLPzPY4iUfLyCLVs");
        openIdList.add("o-mAK5_H-VujM2sT7SYs1pyJEEio");
        openIdList.add("o-mAK56WJbFNVxrptEbo80apufVc");
        openIdList.add("o-mAK55qfZGGZLh-BV4AG78sXgtQ");
        JSONObject jsonObject = UserApi.batchGetUserInfo(openIdList);
        System.out.println(jsonObject.toJSONString());
    }
}

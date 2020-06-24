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

import com.chanus.yuntao.weixin.mp.api.AccessTokenApi;
import com.chanus.yuntao.weixin.mp.api.bean.AccessToken;
import org.junit.Test;

/**
 * AccessTokenApi 测试
 *
 * @author Chanus
 * @date 2020-05-17 17:30:37
 * @since 1.0.0
 */
public class AccessTokenApiTest extends WXConfigTest {
    @Test
    public void getAccessTokenTest() {
        AccessToken accessToken = AccessTokenApi.getAccessToken();
        System.out.println(accessToken.toString());
    }

    @Test
    public void getAccessTokenStrTest() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        System.out.println(accessToken);
    }
}

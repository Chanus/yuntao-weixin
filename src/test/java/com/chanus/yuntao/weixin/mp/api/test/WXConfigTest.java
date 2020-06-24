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
import com.chanus.yuntao.weixin.mp.api.bean.WXConfig;
import org.junit.Before;

/**
 * WXConfig 测试
 *
 * @author Chanus
 * @date 2020-06-06 13:08:38
 * @since 1.0.0
 */
public class WXConfigTest {
    @Before
    public void init() {
        System.out.println("init wxConfig");
        String appId = "wx4c0b9bf56ed6df6a";
        String appSecret = "657ddf71ed42456142380010e2be68a5";
        AccessTokenApi.wxConfig = new WXConfig(appId, appSecret);
    }
}

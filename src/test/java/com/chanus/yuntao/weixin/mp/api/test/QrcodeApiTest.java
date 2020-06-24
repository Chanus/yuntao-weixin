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
import com.chanus.yuntao.weixin.mp.api.QrcodeApi;
import org.junit.Test;

/**
 * QrcodeApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 01:10:12
 * @since 1.0.0
 */
public class QrcodeApiTest extends WXConfigTest {
    @Test
    public void createTemporary() {
        JSONObject jsonObject1 = QrcodeApi.createTemporary(10000, 1);
        System.out.println(jsonObject1.toJSONString());

        JSONObject jsonObject2 = QrcodeApi.createTemporary(10000, "test");
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void createPermanentTest() {
        JSONObject jsonObject1 = QrcodeApi.createPermanent(1);
        System.out.println(jsonObject1.toJSONString());

        JSONObject jsonObject2 = QrcodeApi.createPermanent("test");
        System.out.println(jsonObject2.toJSONString());
    }

    @Test
    public void getShowQrcodeUrlTest() {
        String ticket = "gQEo8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyNjBfaXQwV3djYUQxMDAwMHcwN3UAAgSFccFeAwQAAAAA";
        String qrcodeUrl = QrcodeApi.getShowQrcodeUrl(ticket);

        System.out.println(qrcodeUrl);
    }

    @Test
    public void getShortUrlTest() {
        String longUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEo8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyNjBfaXQwV3djYUQxMDAwMHcwN3UAAgSFccFeAwQAAAAA";
        JSONObject jsonObject = QrcodeApi.getShortUrl(longUrl);
        System.out.println(jsonObject.toJSONString());

        System.out.println(QrcodeApi.getShortUrlStr(longUrl));
    }
}

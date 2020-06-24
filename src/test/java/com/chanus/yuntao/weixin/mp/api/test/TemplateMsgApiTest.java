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
import com.chanus.yuntao.utils.core.DateUtils;
import com.chanus.yuntao.weixin.mp.api.TemplateMsgApi;
import com.chanus.yuntao.weixin.mp.api.bean.TemplateMessage;
import org.junit.Test;

/**
 * TemplateMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 14:47:15
 * @since 1.0.0
 */
public class TemplateMsgApiTest extends WXConfigTest {
    @Test
    public void setIndustryTest() {
        JSONObject jsonObject = TemplateMsgApi.setIndustry("1", "17");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getIndustryTest() {
        JSONObject jsonObject = TemplateMsgApi.getIndustry();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTemplateIdTest() {
        JSONObject jsonObject = TemplateMsgApi.getTemplateId("TM00303");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getAllTemplateTest() {
        JSONObject jsonObject = TemplateMsgApi.getAllTemplate();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delTemplateTest() {
        JSONObject jsonObject = TemplateMsgApi.delTemplate("uYne7qJpCvEbyQCJkZ9wz9axFo8C9bsXs1HgY0bjROU");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void sendTest() {
        String json = TemplateMessage.create().setTouser("o9Q3g0c-95M7vLPzPY4iUfLyCLVs")
                .setTemplate_id("6SihCiqY8XBgK-OUto2MHnA4c8ump7kT55rd-PHyEzo")
                .setUrl("http://t.liulianhuan.com/teacher/register?schoolCode=350524001")
                .setData("first", "老师注册")
                .setData("keyword1", "安溪一中")
                .setData("keyword2", DateUtils.nowDateTime())
                .setData("remark", "请点击注册，感谢您的支持。", "#FF0000")
                .toJSONString();

        JSONObject jsonObject = TemplateMsgApi.send(json);
        System.out.println(jsonObject.toJSONString());
    }
}

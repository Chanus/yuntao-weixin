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
import com.chanus.yuntao.weixin.mp.api.PoiApi;
import com.chanus.yuntao.weixin.mp.api.bean.Poi;
import org.junit.Test;

/**
 * PoiApi 测试
 *
 * @author Chanus
 * @date 2020-06-08 16:50:31
 * @since 1.0.0
 */
public class PoiApiTest extends WXConfigTest {
    @Test
    public void addPoiTest() {
        String json = Poi.toJSONString(Poi.BaseInfo.create().setSid("33788392")
                .setBusiness_name("15个汉字或30个英文字符内")
                .setBranch_name("不超过10个字，不能含有括号和特殊字符")
                .setProvince("不超过10个字").setCity("不超过30个字").setDistrict("不超过10个字")
                .setAddress("门店所在的详细街道地址（不要填写省市信息）：不超过80个字")
                .setTelephone("不超53个字符（不可以出现文字）")
                .setCategories(new String[]{"美食,小吃快餐"}).setOffset_type(1)
                .setLongitude(115.32375).setLatitude(25.097486)
                .addPhoto("https:// 不超过20张.com").addPhoto("https://XXX.com")
                .setRecommend("不超过200字。麦辣鸡腿堡套餐，麦乐鸡，全家桶")
                .setSpecial("不超过200字。免费wifi，外卖服务")
                .setIntroduction("不超过300字。麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、 水果等快餐食品")
                .setOpen_time("8:00-20:00").setAvg_price(35));
        System.out.println(json);

        JSONObject jsonObject = PoiApi.addPoi(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getPoiTest() {
        JSONObject jsonObject = PoiApi.getPoi("271262077");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getPoiListTest() {
        JSONObject jsonObject = PoiApi.getPoiList(0, 20);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void updatePoiTest() {
        String json = Poi.toJSONString(Poi.BaseInfo.create().setPoi_id("271864249").setSid("A00001")
                .setTelephone("020-12345678")
                .addPhoto("https:// XXX.com").addPhoto("https://XXX.com")
                .setRecommend("麦辣鸡腿堡套餐，麦乐鸡，全家桶")
                .setSpecial("免费wifi，外卖服务")
                .setIntroduction("麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品")
                .setOpen_time("8:00-20:00").setAvg_price(35));
        System.out.println(json);

        JSONObject jsonObject = PoiApi.updatePoi(json);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delPoiTest() {
        JSONObject jsonObject = PoiApi.delPoi("271262077");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getWxCategoryTest() {
        JSONObject jsonObject = PoiApi.getWxCategory();
        System.out.println(jsonObject.toJSONString());
    }
}

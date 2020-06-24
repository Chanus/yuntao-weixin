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
import com.chanus.yuntao.weixin.mp.api.DataCubeApi;
import org.junit.Test;

/**
 * DataCubeApi 测试
 *
 * @author Chanus
 * @date 2020-06-08 21:10:11
 * @since 1.0.0
 */
public class DataCubeApiTest extends WXConfigTest {
    @Test
    public void getUserSummaryTest() {
        JSONObject jsonObject = DataCubeApi.getUserSummary("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserCumulateTest() {
        JSONObject jsonObject = DataCubeApi.getUserCumulate("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getArticleSummaryTest() {
        JSONObject jsonObject = DataCubeApi.getArticleSummary("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getArticleTotalTest() {
        JSONObject jsonObject = DataCubeApi.getArticleTotal("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserReadTest() {
        JSONObject jsonObject = DataCubeApi.getUserRead("2020-06-05", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserReadHourTest() {
        JSONObject jsonObject = DataCubeApi.getUserReadHour("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserShareTest() {
        JSONObject jsonObject = DataCubeApi.getUserShare("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUserShareHourTest() {
        JSONObject jsonObject = DataCubeApi.getUserShareHour("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsg("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgHourTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgHour("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgWeekTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgWeek("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgMonthTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgMonth("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgDistTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgDist("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgDistWeekTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgDistWeek("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getUpStreamMsgDistMonthTest() {
        JSONObject jsonObject = DataCubeApi.getUpStreamMsgDistMonth("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void publisherAdPosGeneralTest() {
        JSONObject jsonObject = DataCubeApi.publisherAdPosGeneral(1, 20, "2020-06-01", "2020-06-07", null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void publisherCpsGeneralTest() {
        JSONObject jsonObject = DataCubeApi.publisherCpsGeneral(1, 20, "2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void publisherSettlementTest() {
        JSONObject jsonObject = DataCubeApi.publisherSettlement(1, 20, "2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getInterfaceSummaryTest() {
        JSONObject jsonObject = DataCubeApi.getInterfaceSummary("2020-06-01", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getInterfaceSummaryHourTest() {
        JSONObject jsonObject = DataCubeApi.getInterfaceSummaryHour("2020-06-07", "2020-06-07");
        System.out.println(jsonObject.toJSONString());
    }
}

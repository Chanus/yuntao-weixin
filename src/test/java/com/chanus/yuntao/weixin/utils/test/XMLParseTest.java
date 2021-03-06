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
package com.chanus.yuntao.weixin.utils.test;

import com.chanus.yuntao.weixin.utils.XMLParse;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * XMLParse 测试
 *
 * @author Chanus
 * @date 2020-05-20 08:52:41
 * @since 1.0.0
 */
public class XMLParseTest {
    @Test
    public void extractTest() {
        String xml = "<xml>\n" +
                "<Encrypt><![CDATA[qqqqq]]></Encrypt>\n" +
                "<ToUserName><![CDATA[aaaaaa]]></ToUserName>\n" +
                "<TimeStamp>1589936148698</TimeStamp>\n" +
                "<Nonce><![CDATA[1234]]></Nonce>\n" +
                "</xml>";

        Object[] arr = XMLParse.extract(xml);

        Arrays.asList(arr).forEach(System.out::println);
    }

    @Test
    public void parseXmlToMapTest() {
        String xml = "<xml><ToUserName><![CDATA[gh_f19e07ef435d]]></ToUserName><FromUserName><![CDATA[o9Q3g0c-95M7vLPzPY4iUfLyCLVs]]></FromUserName><CreateTime>1589503479</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_350524001]]></EventKey><Ticket><![CDATA[gQE-8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyREhHNHNGV3djYUQxVEJPQjF1Y1oAAgTl5b1eAwQAjScA]]></Ticket></xml>";
        Map<String, String> map = XMLParse.parseXmlToMap(xml);
        map.forEach((x, y) -> System.out.println(x + "=" + y));
    }

    @Test
    public void generateTest() {
        String xml = XMLParse.generate("qqqqq", "aaaaaa", String.valueOf(System.currentTimeMillis()), "1234");
        System.out.println(xml);
    }
}

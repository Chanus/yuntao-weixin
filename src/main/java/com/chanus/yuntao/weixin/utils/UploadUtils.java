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
package com.chanus.yuntao.weixin.utils;

import com.chanus.yuntao.utils.core.HttpUtils;
import com.chanus.yuntao.utils.core.IOUtils;
import com.chanus.yuntao.utils.core.StreamUtils;
import com.chanus.yuntao.utils.core.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 文件上传工具类
 *
 * @author Chanus
 * @date 2020-07-03 16:05:31
 * @since 1.0.0
 */
public class UploadUtils {
    /**
     * 文件上传
     *
     * @param url  发送请求的 URL
     * @param file 需要上传的文件
     * @return 远程资源的响应结果
     */
    public static String upload(final String url, File file) {
        return HttpUtils.upload(url, file, "media");
    }

    /**
     * 上传视频素材
     *
     * @param url         发送请求的 URL
     * @param file        需要上传的文件
     * @param description 视频素材的描述
     * @return 远程资源的响应结果
     */
    public static String upload(final String url, File file, final String description) {
        DataOutputStream dataOutputStream = null;
        HttpURLConnection connection = null;// URL 连接

        // 必须多两道线
        String twoHyphens = "--";
        // 边界
        String boundary = "*****";
        // 结尾
        String end = StringUtils.CRLF;
        try {
            Map<String, String> headers = HttpUtils.initialBasicHeader();
            headers.put("Charset", "UTF-8");
            headers.put("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection = HttpUtils.getPostConnection(url, headers);

            // 获得输出流
            dataOutputStream = new DataOutputStream(connection.getOutputStream());
            // 输出表头
            String head = twoHyphens + boundary + end +
                    // 上传文件
                    "Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"" + end +
                    // 获取文件类型设置成请求头
                    "Content-Type: application/octet-stream" + end + end;
            dataOutputStream.write(head.getBytes(StandardCharsets.UTF_8));
            // 把文件以流文件的方式推入到 url 中
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, length);
            }
            dataInputStream.close();

            // 提交视频素材的描述信息
            String paramData = end + twoHyphens + boundary + end +
                    "Content-Disposition: form-data;name=\"description\";" +
                    "Content-Type: application/octet-stream" + end + end;
            dataOutputStream.write(paramData.getBytes(StandardCharsets.UTF_8));
            dataOutputStream.write(description.getBytes(StandardCharsets.UTF_8));

            // 定义数据分隔线
            String foot = end + twoHyphens + boundary + twoHyphens + end;
            dataOutputStream.write(foot.getBytes(StandardCharsets.UTF_8));
            dataOutputStream.flush();

            // 读取响应
            return StreamUtils.read2String(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(dataOutputStream);
            HttpUtils.closeConnection(connection);
        }
    }
}

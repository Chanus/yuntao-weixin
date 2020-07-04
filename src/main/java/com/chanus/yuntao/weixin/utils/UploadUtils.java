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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        StringBuilder result = new StringBuilder();// 返回的结果
        DataOutputStream dataOutputStream = null;
        BufferedReader bufferedReader = null;// 读取响应输入流

        // 必须多两道线
        String twoHyphens = "--";
        // 边界
        String boundary = "*****";
        // 结尾
        String end = "\r\n";
        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection connection = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性，请求头信息
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 设定请求的方法，默认是GET
            connection.setRequestMethod("POST");
            // 设置是否向 connection 输出
            connection.setDoOutput(true);
            // 设置是否从 connection 读入，默认情况下是true
            connection.setDoInput(true);
            // POST 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            // 建立实际的连接
            connection.connect();

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
            // 定义BufferedReader输入流来读取URL的响应，并设置编码方式
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Request exception", e);
        } finally {
            IOUtils.close(dataOutputStream);
            IOUtils.close(bufferedReader);
        }
        return result.toString();
    }
}

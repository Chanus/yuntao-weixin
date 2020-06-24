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
package com.chanus.yuntao.weixin.mp.api.bean;

/**
 * 媒体文件类型枚举
 *
 * @author Chanus
 * @date 2020-06-06 23:48:00
 * @since 1.0.0
 */
public enum MediaTypeEnum {
    // 图片
    IMAGE,
    // 语音
    VOICE,
    // 视频
    VIDEO,
    // 缩略图
    THUMB,
    // 图文
    NEWS;

    public String get() {
        return this.name().toLowerCase();
    }
}

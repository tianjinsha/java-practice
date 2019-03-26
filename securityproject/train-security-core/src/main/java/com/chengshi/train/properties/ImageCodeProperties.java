package com.chengshi.train.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties{
    /**
     * 图片验证码的宽度
     */
    private int width = 67;
    /**
     * 图片验证码的高度
     */
    private int height = 23;

    /**
     * 设置默认长度为4
     */
    public ImageCodeProperties() {
        setLength(4);
    }
}

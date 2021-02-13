package org.example.security.core.properties;

import lombok.Data;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 16:05
 */
@Data
public class ImageCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 4;
    /**
     * 验证码过期时间(分钟)
     */
    private int expireIn = 60;

    /**
     * 图片验证码的宽度
     */
    private int width = 110;
    /**
     * 图片验证码的高度
     */
    private int height = 40;

    private String url;

}

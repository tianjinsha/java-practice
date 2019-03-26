package com.chengshi.train.validate.image;

import com.chengshi.train.validate.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 *
 * 图片验证码
 */
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage bufferedImage;

    public  ImageCode(){}

    /**
     * 图片验证构造
     * @param bufferedImage
     * @param code
     * @param expireTime 过期具体时间
     */
    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code, expireTime);

        this.bufferedImage = bufferedImage;
    }

    /**
     * 图片验证构造
     * @param bufferedImage
     * @param code
     * @param expireSec 过期的秒数
     */
    public ImageCode(BufferedImage bufferedImage, String code, int expireSec) {
        super(code, expireSec);
        this.bufferedImage = bufferedImage;
    }
}

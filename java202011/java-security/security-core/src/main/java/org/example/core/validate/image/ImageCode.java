package org.example.core.validate.image;

import lombok.Data;
import org.example.core.validate.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author tjstj
 * @description 图片验证码
 * @date 2021/1/24 15:20
 */
@Data
public class ImageCode  extends ValidateCode {
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

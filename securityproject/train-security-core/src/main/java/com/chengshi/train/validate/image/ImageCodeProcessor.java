package com.chengshi.train.validate.image;

import com.chengshi.train.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 *  * 图像验证码处理器
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor  extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getBufferedImage(), "PNG", request.getResponse().getOutputStream());
    }
}

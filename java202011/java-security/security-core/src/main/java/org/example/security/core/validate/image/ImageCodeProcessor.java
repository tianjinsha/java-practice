package org.example.security.core.validate.image;

import org.example.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:51
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getBufferedImage(), "PNG", request.getResponse().getOutputStream());
    }
}

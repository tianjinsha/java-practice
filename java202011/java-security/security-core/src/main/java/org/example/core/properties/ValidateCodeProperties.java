package org.example.core.properties;

import lombok.Data;

/**
 * @author: tjshan
 * @date: 2021/1/17 1:41 下午
 * FileName: ValidateCodeProperties
 * Description:
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}

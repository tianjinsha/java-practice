package org.example.security.core.validate.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/30 23:20
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        log.info("向手机"+mobile+"发送短信验证码"+code);
    }
}

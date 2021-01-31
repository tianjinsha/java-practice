package org.example.core.validate.sms;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/30 23:19
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     * @param mobile 电话号码
     * @param code 验证码
     */
    void send(String mobile, String code);
}

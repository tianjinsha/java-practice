package com.chengshi.train.validate.sms;

/**
 * 短信验证码发送器接口
 * @author tian
 * @date 2018年11月27日
 */
public interface SmsCodeSender {

	void send(String mobile, String code);
}

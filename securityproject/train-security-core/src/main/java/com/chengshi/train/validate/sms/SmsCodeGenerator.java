package com.chengshi.train.validate.sms;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.chengshi.train.validate.ValidateCode;
import com.chengshi.train.validate.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 * @author tian
 * @date 2018年11月27日
 */
@Data
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private TrainSecurityProperties properties;
	
	@Override
	public ValidateCode generate(ServletWebRequest servletWebRequest) {
		String code = RandomStringUtils.randomNumeric(properties.getCode().getSms().getLength());
		return new ValidateCode(code, properties.getCode().getSms().getExpireIn());
	}
	

}

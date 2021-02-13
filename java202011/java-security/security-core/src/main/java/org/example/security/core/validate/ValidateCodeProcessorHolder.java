package org.example.security.core.validate;

import org.example.security.core.validate.exception.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tjstj
 * @description 校验码处理器
 * @date 2021/1/24 15:25
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}

package org.example.security.core.validate;

import lombok.extern.slf4j.Slf4j;
import org.example.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:24
 */
@RestController
@Slf4j
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }
}

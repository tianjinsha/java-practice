package org.example.security.app.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author tjstj
 * @description 自定义oauth2异常
 * @date 2021/2/15 20:35
 */
@JsonSerialize(using = AppOauth2ExceptionSerializer.class)
public class AppOauth2Exception extends OAuth2Exception {
    private Integer status = 400;
    private String OAuth2ErrorCode;


    public AppOauth2Exception(String msg) {
        super(msg);
    }

    public AppOauth2Exception(String message, Throwable t) {
        super(message, t);
        status = ((OAuth2Exception)t).getHttpErrorCode();
        OAuth2ErrorCode = ((OAuth2Exception)t).getOAuth2ErrorCode();
    }

    @Override
    public int getHttpErrorCode() {
        return status;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return OAuth2ErrorCode;
    }
}

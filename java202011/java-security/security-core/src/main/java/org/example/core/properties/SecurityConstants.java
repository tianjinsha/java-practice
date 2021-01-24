package org.example.core.properties;

/**
 * @author: tjshan
 * @date: 2021/1/17 1:43 下午
 * FileName: SecurityConstants
 * Description:
 */
public class SecurityConstants {


    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     * @see org.example.core.controller.SecurityController
     */
    public static final String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认登录页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/signIn.html";

    /**
     * 默认失败页面
     */
    public static final String DEFAULT_LOGIN_FAIL_PAGE_URL = "/signIn-error.html";

    /**
     * 登出默认出口
     */
    public static final String DEFAULT_LOGOUT_PAGE_URL = "/logout";

    /**
     * 默认注册页面
     */
    public static final String DEFAULT_REGISTER_PAGE_URL = "/signUp.html";

    /**
     * session失效默认的跳转页面
     */
    public static final String DEFAULT_SESSION_INVALID_PAGE = "/session-invalid.html";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE =  "mobile";
}

package org.example.security.core.validate.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.security.core.authentication.AuthenticationFailureHandler;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.SecurityConstants;
import org.example.security.core.validate.ValidateCodeProcessorHolder;
import org.example.security.core.validate.ValidateCodeType;
import org.example.security.core.validate.exception.ValidateCodeException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 15:16
 */
@Slf4j
//@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    ProjectSecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 存放所有需要校验验证码的url
     */
    private final  Map<String, ValidateCodeType> urlMaps;

    protected ValidateCodeFilter(){
        super();
        urlMaps = new HashMap<>(8);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            log.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
                log.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                log.info("验证码校验不通过");
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 初始化要拦截的url配置信息
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        Object[] objects = urlMaps.entrySet().toArray();
        System.out.println(objects);
        System.out.println(objects.length);

        urlMaps.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMaps.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMaps.put(url, type);
            }
        }
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;

        if (!StringUtils.equalsIgnoreCase(request.getMethod(), RequestMethod.GET.name())) {
            Set<String> urls = urlMaps.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMaps.get(url);
                }
            }
        }
        return result;
    }
}

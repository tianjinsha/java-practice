package org.example.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.core.constant.CommonErrorCodeBase;
import org.example.core.constant.ResultEnum;
import org.example.security.core.properties.ProjectSecurityProperties;
import org.example.security.core.properties.ResponseType;
import org.example.core.protocol.CommonBean;
import org.example.core.protocol.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 12:29
 */
@Slf4j
public class AbstractSessionStrategy {

    /**
     * 跳转的url
     */
    private String invalidSessionUrl;
    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 跳转前是否创建新的session
     */
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProjectSecurityProperties securityProperties;

    /**
     * @param invalidSessionUrl
     */
    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.invalidSessionUrl = invalidSessionUrl;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.session.InvalidSessionStrategy#
     * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (createNewSession) {
            request.getSession();
        }
        if (securityProperties.getResponseType().equals(ResponseType.HTML.name())) {
            log.info("session失效,跳转到" + invalidSessionUrl);
            redirectStrategy.sendRedirect(request, response, invalidSessionUrl);
        } else {
            String message = "session失效";
            if (isConcurrency()) {
                message = message + "，你的账号在另一地点被登录";
            }
            message = message + "，如果密码泄露，请立即修改密码";
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            CommonResult result = new CommonResult();
            result.setResult(ResultEnum.Fail.getResult());
            result.setMessage(ResultEnum.Fail.getMessage());
            CommonBean bean = new CommonBean(CommonErrorCodeBase.UNAUTHORIZED, message);
            result.setCommand(request.getRequestURI());
            result.setParam(bean);
            response.getWriter().flush();
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    /**
     * session失效是否是并发导致的
     *
     * @return boolean
     */
    protected boolean isConcurrency() {
        return false;
    }

    /**
     * Determines whether a new session should be created before redirecting (to
     * avoid possible looping issues where the same session ID is sent with the
     * redirected request). Alternatively, ensure that the configured URL does
     * not pass through the {@code SessionManagementFilter}.
     *
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}

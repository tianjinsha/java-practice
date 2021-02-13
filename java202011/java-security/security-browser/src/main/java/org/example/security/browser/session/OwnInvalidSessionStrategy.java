package org.example.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 12:55
 */
public class OwnInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {
    /**
     * @param invalidSessionUrl
     */
    public OwnInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);

    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);

    }
}

package org.example.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/1/24 12:54
 */
public class OwnExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    /**
     * @param invalidSessionUrl 失效地址
     */
    public OwnExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());

    }
}

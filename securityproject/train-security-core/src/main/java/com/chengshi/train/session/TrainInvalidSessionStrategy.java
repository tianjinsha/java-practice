package com.chengshi.train.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-31 23:40
 */
public class TrainInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public TrainInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}

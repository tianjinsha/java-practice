package com.chengshi.train.session;
import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-31 23:17
 */
public class TrainExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public TrainExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }


}

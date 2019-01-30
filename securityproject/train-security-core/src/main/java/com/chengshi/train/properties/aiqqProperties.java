package com.chengshi.train.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

public class aiqqProperties extends SocialProperties {
    private String   providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}

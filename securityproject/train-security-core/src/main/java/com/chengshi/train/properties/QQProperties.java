package com.chengshi.train.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
@Data
public class QQProperties extends org.springframework.boot.autoconfigure.social.SocialProperties{

    private String providerId = "qq";

}

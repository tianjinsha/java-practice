package com.chengshi.train.properties;

import lombok.Data;

@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    QQProperties qq=new QQProperties();
}

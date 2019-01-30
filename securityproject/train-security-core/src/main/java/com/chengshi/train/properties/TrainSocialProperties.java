package com.chengshi.train.properties;

import lombok.Data;

@Data
public class TrainSocialProperties {

    private String filterProcessesUrl = "/auth";

    QQProperties qq=new QQProperties();

    WeixinProperties weixin=new WeixinProperties();
}

package com.chengshi.train.properties;

import lombok.Data;

/**
 * 短信验证码的相关属性
 */
@Data
public class SmsCodeProperties {
    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 验证码过期时间(分钟)
     */
    private int expireIn = 60;
    /**
     * 验证码的生成地址
     */
    private String url;

    // 短信应用SDK AppID
    private int appid = 1400168552; // 1400开头

    // 短信应用SDK AppKey
    private String appkey ="dd5aa4b06ac6de9071fa06c85a11f7da";

    // 短信模板ID，需要在短信应用中申请
    private int templateId=274149;

    // 签名
    private String smsSign = "田金山的网站分享快乐";

}

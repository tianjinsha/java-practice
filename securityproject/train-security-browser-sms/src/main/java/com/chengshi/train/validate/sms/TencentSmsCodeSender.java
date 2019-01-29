package com.chengshi.train.validate.sms;

import com.chengshi.train.properties.TrainSecurityProperties;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component("smsCodeSender")
public class TencentSmsCodeSender implements SmsCodeSender{

    @Autowired
    private TrainSecurityProperties properties;

    @Override
    public void send(String mobile, String code) {
        int appid = properties.getCode().getSms().getAppid();
        String appkey = properties.getCode().getSms().getAppkey();
        int templateId=properties.getCode().getSms().getTemplateId();
        String smsSign = properties.getCode().getSms().getSmsSign();
        try {
            String[] params = {code,String.valueOf(properties.getCode().getSms().getExpireIn())};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86",mobile,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            log.info("短信验证码："+code);
            log.info(result.toString());
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int appid =1400168552; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "dd5aa4b06ac6de9071fa06c85a11f7da";

        int templateId = 274064;

        String[] phoneNumbers = {"18780183730", "13882033424"};

        String smsSign = "田金山的网站分享快乐";



    }
}

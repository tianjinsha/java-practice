package com.chengshi.train.social.qq.connect;

import com.chengshi.train.social.qq.api.QQ;
import com.chengshi.train.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ qq) {
        //判断qq请求是否通畅
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        // 设置服务商的id  openid
        values.setProviderUserId(userInfo.getOpenId());
        // 设置显示用户名
        values.setDisplayName(userInfo.getNickname());
        // 设置头像url
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // qq没有个人主页，所以设置为空
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}

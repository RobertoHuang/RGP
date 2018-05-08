/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQAdapter
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 10:48
 * Description: 适配SpringSocial默认的返回信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import roberto.growth.process.security.core.social.qq.api.QQ;
import roberto.growth.process.security.core.social.qq.api.QQUserInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈适配SpringSocial默认的返回信息〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08
 * @since 1.0.0
 */
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        // 唯一标识
        values.setProviderUserId(userInfo.getOpenId());
        // 用户昵称
        values.setDisplayName(userInfo.getNickname());
        // 头像地址
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 个人主页地址
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinAdapter
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 8:07
 * Description: 将微信API返回的数据模型适配SpringSocial的标准模型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import roberto.growth.process.security.core.social.weixin.api.WeiXin;
import roberto.growth.process.security.core.social.weixin.api.WeiXinUserInfo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈将微信API返回的数据模型适配SpringSocial的标准模型〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
public class WeiXinAdapter implements ApiAdapter<WeiXin> {
    private String openId;

    public WeiXinAdapter() {

    }

    public WeiXinAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeiXin api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin api, ConnectionValues values) {
        WeiXinUserInfo userInfo = api.getUserInfo(openId);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin api) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin api, String message) {

    }
}
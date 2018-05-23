/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinImpl
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 7:48
 * Description: 微信 API实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信 API实现类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10
 * @since 1.0.0
 */
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {
    /** 获取用户信息的URL **/
    private static final String WEIXIN_URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WeiXinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeiXinUserInfo getUserInfo(String openId) {
        String url = WEIXIN_URL_GET_USER_INFO + openId;
        String result = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(result, "errcode")) {
            return null;
        }
        try {
            WeiXinUserInfo userInfo = ((JSONObject) JSONObject.parse(result)).toJavaObject(WeiXinUserInfo.class);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }

    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        // 使用UTF-8 替换默认的ISO-8859-1编码
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
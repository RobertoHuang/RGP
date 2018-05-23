/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinAccessGrant
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 7:59
 * Description: 微信返回的AccessToken类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.connet;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信返回的AccessToken类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
@Getter
@Setter
public class WeiXinAccessGrant extends AccessGrant {
    private static final long serialVersionUID = 2607449538707705825L;

    private String openId;

    public WeiXinAccessGrant() {
        super("");
    }

    public WeiXinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }
}
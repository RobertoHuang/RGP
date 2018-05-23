/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXin
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 7:42
 * Description: 微信 API接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.api;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信 API接口〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10
 * @since 1.0.0
 */
public interface WeiXin {
    /**
     * 功能描述: <br>
     * 〈获取微信返回用户信息〉
     *
     *  QQ的getUserInfo微信多了一个参数openId
     *  这是因为微信文档中在OAuth2.0的认证流程示意图第五步时，微信的openid 同access_token一起返回
     *  而Spring Social获取access_token的类AccessGrant.java中没有openid。因此我们自己需要扩展一下Spring Social获取令牌的类（AccessGrant.java）
     *
     * @param openId
     * @return roberto.growth.process.security.core.social.weixin.api.WeiXinUserInfo
     * @author HuangTaiHong
     * @date 2018.05.10 19:48:02
     */
    WeiXinUserInfo getUserInfo(String openId);
}
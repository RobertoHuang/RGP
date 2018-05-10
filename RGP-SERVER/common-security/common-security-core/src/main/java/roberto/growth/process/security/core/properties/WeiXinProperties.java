/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinProperties
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 8:30
 * Description: 微信登录配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信登录配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
@Getter
@Setter
public class WeiXinProperties {
    private String appId;

    private String appSecret;

    /**  providerId用来决定发起第三方登录的URL，默认是Weixin **/
    private String providerId = "weixin";
}
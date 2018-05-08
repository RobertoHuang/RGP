/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: BrowserProperties
 * Author:   HuangTaiHong
 * Date:     2018-02-22 下午 5:21
 * Description: 浏览器配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈浏览器配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Getter
@Setter
public class BrowserProperties {
    /** 登录页面地址 **/
    private String signInPage = "/authentication/toLogin";

    /** 注册页面地址 **/
    private String signUpPage = "/authentication/toRegister";

    /** 用户名密码登录处理请求地址 **/
    private String formLoginProcessUrl = "/authentication/form";

    /** 手机短信验证码登录处理请求地址 **/
    private String mobileLoginProcessUrl = "/authentication/mobile";

    /** Rememberme有效时间(默认为一周) **/
    private Integer rememberMeSeconds = 604800;
}
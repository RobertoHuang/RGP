/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerSecurityProperties
 * Author:   HuangTaiHong
 * Date:     2018-02-22 下午 5:20
 * Description: 安全认证相关配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈安全认证相关配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "roberto.growth.process.security")
public class CustomerSecurityProperties {
    /** 浏览器配置属性 **/
    private BrowserProperties browser = new BrowserProperties();

    /** 验证码配置属性 **/
    private CaptchaProperties captcha = new CaptchaProperties();

    /** 用户中心配置属性 **/
    private UserCenterProperties uc = new UserCenterProperties();
}
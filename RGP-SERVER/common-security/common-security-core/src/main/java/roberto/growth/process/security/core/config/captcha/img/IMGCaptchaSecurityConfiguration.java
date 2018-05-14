/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaSecurityConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/14 21:54
 * Description: 图形验证码安全配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.img;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * 〈一句话功能简述〉<br>
 * 〈图形验证码安全配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Configuration
public class IMGCaptchaSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource(name = "imgCaptchaAuthenticationFilter")
    private Filter imgCaptchaAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(imgCaptchaAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaAuthenticationUserDetailsService
 * Author:   HuangTaiHong
 * Date:     2018/5/14 22:10
 * Description: 短信验证码用户相关Service层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.sms.authentication;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信验证码用户相关Service层〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Setter
public class SMSCaptchaAuthenticationUserDetailsService implements UserDetailsService {
    private RestTemplate restTemplate = new RestTemplate();

    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String url = customerSecurityProperties.getUc().getUrl() + "/user/getByPhoneNumber";
//        HttpEntity<JSONObject> request = new HttpEntity<>(new JSONObject());
//        JSONObject jsonObject = restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class, new Object()).getBody();
//        RGPSecurityUserDetails userDetails = jsonObject.getJSONObject("data").getJSONObject("userDetail").toJavaObject(RGPSecurityUserDetails.class);
//        return userDetails;
        return new UserDetails() {
            private static final long serialVersionUID = -1866902516098613537L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return "dreamT";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }
}
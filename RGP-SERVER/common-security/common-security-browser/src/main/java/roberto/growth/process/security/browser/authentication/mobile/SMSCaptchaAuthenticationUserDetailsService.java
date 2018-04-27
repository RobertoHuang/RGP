/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaAuthenticationUserDetailsService
 * Author:   HuangTaiHong
 * Date:     2018-04-26 下午 2:57
 * Description: 短信验证码用户相关Service层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.authentication.mobile;

import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;
import roberto.growth.process.security.core.entity.RGPSecurityUserDetails;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈短信验证码用户相关Service层〉
 *
 * @author HuangTaiHong
 * @create 2018-04-26
 * @since 1.0.0
 */
@Setter
public class SMSCaptchaAuthenticationUserDetailsService implements UserDetailsService {
    private RestTemplate restTemplate = new RestTemplate();

    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String url = customerSecurityProperties.getUc().getUrl() + "/user/getByPhoneNumber";
        HttpEntity<JSONObject> request = new HttpEntity<>(new JSONObject());
        JSONObject jsonObject = restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class, new Object()).getBody();
        RGPSecurityUserDetails userDetails = jsonObject.getJSONObject("data").getJSONObject("userDetail").toJavaObject(RGPSecurityUserDetails.class);
        return userDetails;
    }
}
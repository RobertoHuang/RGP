/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerUserDetailsService
 * Author:   HuangTaiHong
 * Date:     2018-02-22 下午 4:06
 * Description: 自定义UserDetailsService类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义UserDetailsService类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode("dreamT"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityUserDetails
 * Author:   HuangTaiHong
 * Date:     2018-04-04 上午 11:54
 * Description: RGP Security账户管理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import roberto.growth.process.common.entity.BaseEntity;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈RGP Security账户管理类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-04 
 * @since 1.0.0
 */
@Getter
@Setter
public class RGPSecurityUserDetails extends BaseEntity implements UserDetails{
    private static final long serialVersionUID = 5447559210340285147L;

    /**
     * 邮箱地址
     **/
    private String emailAddress;

    /**
     * 手机号码
     **/
    private String phoneNumber;

    /**
     * 账户密码
     **/
    private String password;

    /**
     * 账户角色
     **/
    private String roleKey;

    /**
     * 账户是否过期
     **/
    private Boolean accountNonExpired;

    /**
     * 账户是否锁定
     **/
    private Boolean accountNonLocked;

    /**
     * 凭证是否过期
     **/
    private Boolean credentialsNonExpired;

    /**
     * 账户是否可用
     **/
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
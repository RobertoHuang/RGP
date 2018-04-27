///**
// * Copyright (C), 2015-2018, ND Co., Ltd.
// * FileName: RGPSecurityAuthenticationToken
// * Author:   HuangTaiHong
// * Date:     2018-04-04 下午 2:31
// * Description: 自定义校验规则
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package roberto.growth.process.security.core.authentication;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈自定义校验规则〉
// *
// * @author HuangTaiHong
// * @create 2018-04-04
// * @since 1.0.0
// */
//@Getter
//@Setter
//public class RGPSecurityAuthenticationToken extends UsernamePasswordAuthenticationToken {
//    private static final long serialVersionUID = -5957226385469374621L;
//
//    /**
//     * 手机号码
//     **/
//    private String mobile;
//
//    /**
//     * 短信验证码
//     **/
//    private String SMSCode;
//
//    /**
//     * 默认用户名密码校验
//     **/
//    private AuthType authType = AuthType.PASSWORD;
//
//
//    public RGPSecurityAuthenticationToken(Object principal, Object credentials) {
//        super(principal, credentials);
//    }
//
//    public RGPSecurityAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
//        super(principal, credentials, authorities);
//    }
//
//    public static enum AuthType {
//        PASSWORD, SMSCAPTCHA;
//    }
//}
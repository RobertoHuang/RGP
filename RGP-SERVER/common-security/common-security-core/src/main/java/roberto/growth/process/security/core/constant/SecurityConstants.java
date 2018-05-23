/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SecurityConstants
 * Author:   HuangTaiHong
 * Date:     2018-03-27 下午 6:55
 * Description: 安全配置常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈安全配置常量〉
 *
 * @author HuangTaiHong
 * @create 2018-03-27
 * @since 1.0.0
 */
public class SecurityConstants {
    /** 静态资源 **/
    public static final String STATIC_RESOURCE = "/static/**";

    /** 验证图片验证码时，HTTP请求中默认的携带图片验证码信息的参数的名称 **/
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMG = "imgCode";

    /** 请求发送短信验证码时，HTTP请求中默认的携带手机号码信息的参数的名称 **/
    public static final String PARAMETER_NAME_SMS_CODE_REQUEST = "mobile";

    /** 验证短信验证码时，HTTP请求中默认的携带短信验证码信息的参数的名称 **/
    public static final String PARAMETER_NAME_SMS_CODE_VALIDATE = "smsCode";

    /** 创建验证码URL地址前缀 **/
    public static final String GENERATE_CAPTCHA_URL_PREFIX = "/generateCaptcha";

    /** 创建验证码URL地址不拦截 **/
    public static final String GENERATE_CAPTCHA_URL_NOT_INTERCEPT = "/generateCaptcha/*";

    /** 验证码SESSION KEY的前缀 **/
    public static final String CAPTCHA_SESSION_KEY_PREFIX = "CAPTCHA_SESSION_KEY_";

    /** Session失效默认的跳转地址 **/
    public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";


}
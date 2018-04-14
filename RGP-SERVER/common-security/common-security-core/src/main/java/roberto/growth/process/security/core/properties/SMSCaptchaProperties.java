/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaProperties
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 2:08
 * Description: 短信验证码配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信验证码配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28 
 * @since 1.0.0
 */
@Getter
@Setter
public class SMSCaptchaProperties {
    /**
     * 验证码文字长度
     **/
    private Integer length = 4;

    /**
     * 验证码过期时间
     **/
    private Integer expireIn = 300;

    /**
     * 需要验证码的请求
     **/
    private String filterURL;
}
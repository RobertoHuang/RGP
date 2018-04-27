/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptcha
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 11:05
 * Description: 短信验证码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈短信验证码〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Getter
@Setter
public class SMSCaptcha extends BaseCaptcha {
    /** 验证码 **/
    private String code;

    /** 手机号码 **/
    private String mobile;

    public SMSCaptcha(String mobile, String code, int expireIn) {
        super(expireIn);
        this.code = code;
        this.mobile = mobile;
    }
}
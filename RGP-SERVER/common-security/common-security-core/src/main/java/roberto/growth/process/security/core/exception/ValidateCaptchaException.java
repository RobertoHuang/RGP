/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ValidateCaptchaException
 * Author:   HuangTaiHong
 * Date:     2018-03-29 下午 7:07
 * Description: 校验验证码异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 〈一句话功能简述〉<br>
 * 〈校验验证码异常〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
public class ValidateCaptchaException extends AuthenticationException {
    public ValidateCaptchaException(String message) {
        super(message);
    }

    public ValidateCaptchaException(String message, Throwable e) {
        super(message, e);
    }
}
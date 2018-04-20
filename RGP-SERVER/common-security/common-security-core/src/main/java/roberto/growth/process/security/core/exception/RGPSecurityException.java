/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityException
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 3:08
 * Description: RGP安全模块通用异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.exception;

import roberto.growth.process.common.exception.RGPBaseException;
import roberto.growth.process.security.core.enums.RGPSecurityExceptionCodeEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP安全模块通用异常〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
public class RGPSecurityException extends RGPBaseException {
    private static final long serialVersionUID = 6353680232816768748L;

    public RGPSecurityException(RGPSecurityExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue());
    }

    public RGPSecurityException(RGPSecurityExceptionCodeEnum exceptionCodeEnum, Throwable e) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue(), e);
    }
}
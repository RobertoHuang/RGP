/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityRTException
 * Author:   HuangTaiHong
 * Date:     2018-05-15 上午 11:24
 * Description: RGP安全模块运行时期异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.exception;

import roberto.growth.process.common.exception.RGPBaseRTException;
import roberto.growth.process.security.core.enums.RGPSecurityExceptionCodeEnum;

/**
 * 〈一句话功能简述〉<br> 
 * 〈RGP安全模块运行时期异常〉
 *
 * @author HuangTaiHong
 * @create 2018-05-15 
 * @since 1.0.0
 */
public class RGPSecurityRTException extends RGPBaseRTException{
    private static final long serialVersionUID = 5419355150820166304L;

    public RGPSecurityRTException(RGPSecurityExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue());
    }

    public RGPSecurityRTException(RGPSecurityExceptionCodeEnum exceptionCodeEnum, Throwable e) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue(), e);
    }
}
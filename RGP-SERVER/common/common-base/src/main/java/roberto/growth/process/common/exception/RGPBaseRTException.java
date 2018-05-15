/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPBaseRTException
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 6:43
 * Description: 通用运行时期异常 用于继承
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception;

import roberto.growth.process.common.exception.base.impl.AbstractCommonRTException;
import roberto.growth.process.common.exception.enums.RGPBaseExceptionCodeEnum;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通用运行时期异常 用于继承〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26 
 * @since 1.0.0
 */
public class RGPBaseRTException extends AbstractCommonRTException{
    private static final long serialVersionUID = -8066439820378767125L;

    public RGPBaseRTException(String code, String message) {
        super(code, message);
    }

    public RGPBaseRTException(String code, String message, Throwable e) {
        super(code, message, e);
    }

    public RGPBaseRTException(RGPBaseExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue());
    }

    public RGPBaseRTException(RGPBaseExceptionCodeEnum exceptionCodeEnum, Throwable e) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue(), e);
    }
}
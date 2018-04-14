/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPBaseException
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 2:11
 * Description: 通用业务异常基类 用于继承
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception;

import roberto.growth.process.common.exception.base.impl.AbstractCommonBizException;
import roberto.growth.process.common.exception.enums.RGPBaseExceptionCodeEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈通用业务异常基类 用于继承〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public class RGPBaseException extends AbstractCommonBizException {
    private static final long serialVersionUID = -8348789370492909719L;

    private Object[] params;

    public RGPBaseException(String code, String message) {
        super(code, message);
    }

    public RGPBaseException(String code, String message, Throwable e) {
        super(code, message, e);
    }

    public RGPBaseException(RGPBaseExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue());
    }

    public RGPBaseException(RGPBaseExceptionCodeEnum exceptionCodeEnum, Throwable e) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue(), e);
    }

    public RGPBaseException(String code, Object[] params, String msg) {
        this(code, msg);
        this.params = params;
    }

    public RGPBaseException(String code, Object[] params, String msg, Throwable e) {
        this(code, msg, e);
        this.params = params;
    }

    public Object[] getParams() {
        return this.params;
    }
}
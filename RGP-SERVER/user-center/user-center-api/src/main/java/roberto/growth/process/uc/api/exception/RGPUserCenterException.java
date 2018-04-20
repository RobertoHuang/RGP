/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPUserCenterException
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 1:51
 * Description: RGP用户中心通用异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.exception;

import roberto.growth.process.common.exception.RGPBaseException;
import roberto.growth.process.uc.api.enums.RGPUserCenterExceptionCodeEnum;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP用户中心通用异常〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18
 * @since 1.0.0
 */
public class RGPUserCenterException extends RGPBaseException {
    private static final long serialVersionUID = -7761474793877255578L;

    public RGPUserCenterException(RGPUserCenterExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue());
    }

    public RGPUserCenterException(RGPUserCenterExceptionCodeEnum exceptionCodeEnum, Throwable e) {
        super(exceptionCodeEnum.getCode(), exceptionCodeEnum.getValue(), e);
    }
}
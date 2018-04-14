/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AbstractCommonBizException
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 12:02
 * Description: 抽象通用业务异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception.base.impl;

import roberto.growth.process.common.exception.base.IExceptionWrapper;
import roberto.growth.process.common.exception.model.ExceptionDetailsInfo;
import roberto.growth.process.common.exception.utils.CommonExceptionUtils;
import roberto.growth.process.common.utils.SystemPropertiesUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈抽象通用业务异常〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public class AbstractCommonBizException extends Exception implements IExceptionWrapper {
    private static final long serialVersionUID = 6219026922727344025L;

    private String code;
    private final Long timestamp;
    private ExceptionDetailsInfo exceptionDetailsInfo;

    private final transient Throwable cause;
    private final transient IExceptionWrapper parent;

    public AbstractCommonBizException(String code, String message) {
        super(message);
        this.cause = null;
        this.parent = null;
        this.code = buildExceptionCode(code);
        this.timestamp = System.currentTimeMillis();
        this.exceptionDetailsInfo = CommonExceptionUtils.wrapperExceptionDetailsInfo(this);
    }

    public AbstractCommonBizException(String code, String message, Throwable e) {
        super(message);
        if (e instanceof IExceptionWrapper) {
            this.cause = e;
            this.parent = (IExceptionWrapper) e;
            this.code = buildExceptionCode(code);
            this.timestamp = ((IExceptionWrapper) e).getTimestamp();
            this.exceptionDetailsInfo = ((IExceptionWrapper) e).getExceptionDetailsInfo();
        } else {
            this.cause = e;
            this.parent = null;
            this.code = buildExceptionCode(code);
            this.timestamp = System.currentTimeMillis();
            this.exceptionDetailsInfo = CommonExceptionUtils.wrapperExceptionDetailsInfo(this);
        }
    }

    @Override
    public synchronized Throwable getCause() {
        return (cause==this ? null : cause);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public Long getTimestamp() {
        return this.timestamp;
    }

    @Override
    @SuppressWarnings("all")
    public ExceptionDetailsInfo getExceptionDetailsInfo() {
        if (exceptionDetailsInfo != null) {
            return exceptionDetailsInfo;
        } else {
            if (parent != null) {
                exceptionDetailsInfo = parent.getExceptionDetailsInfo();
                return exceptionDetailsInfo;
            } else {
                try {
                    exceptionDetailsInfo = CommonExceptionUtils.wrapperExceptionDetailsInfo(this);
                } catch (Exception e) {
                    exceptionDetailsInfo = null;
                }
                return exceptionDetailsInfo;
            }
        }
    }

    /**
     * 功能描述: <br>
     * 〈在异常码前拼接上项目名称〉
     *
     * @param code
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 12:03
     */
    private String buildExceptionCode(String code) {
        String projectName = SystemPropertiesUtils.getInstance().getProjectName();
        return new StringBuffer().append(projectName).append("_").append(code).toString();
    }

    @Override
    public String toString() {
        String code = getCode();
        String message = getLocalizedMessage();
        String exceptionName = getClass().getName();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(exceptionName).append(":");
        stringBuffer.append("code[ ").append(code).append(" ], ");
        stringBuffer.append("message[ ").append(message).append(" ]");
        return stringBuffer.toString();
    }
}
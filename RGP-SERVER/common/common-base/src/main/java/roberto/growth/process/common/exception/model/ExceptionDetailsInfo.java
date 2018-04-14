/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ExceptionDetailsInfo
 * Author:   HuangTaiHong
 * Date:     2018-02-26 上午 11:17
 * Description: 异常详细信息实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈异常详细信息实体类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
@Getter
@Setter
public class ExceptionDetailsInfo implements Serializable {
    private static final long serialVersionUID = -2534272341427020445L;

    private String code;
    private String exceptionClass;
    private String exceptionMessage;
    private String exceptionOccurClass;
    private String exceptionOccurMethod;
    private String exceptionOccurLineNumber;

    private String rootExceptionClass;
    private String rootExceptionMessage;
    private String rootExceptionOccurClass;
    private String rootExceptionOccurMethod;
    private String rootExceptionOccurLineNumber;

    private String parameterValue;
    private String exceptionOccurTime;
    private String exceptionFullStackTrace;

    @Override
    public String toString() {
        String code = this.code;
        String message = this.exceptionMessage;
        String exceptionName = this.exceptionClass;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(exceptionName).append(":");
        stringBuffer.append("code[ ").append(code).append(" ], ");
        stringBuffer.append("message[ ").append(message).append(" ]");
        return stringBuffer.toString();
    }
}
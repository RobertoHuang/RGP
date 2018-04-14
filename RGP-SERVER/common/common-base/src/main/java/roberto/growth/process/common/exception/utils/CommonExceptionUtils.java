/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CommonExceptionUtils
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 12:05
 * Description: 通用异常相关工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception.utils;

import roberto.growth.process.common.exception.base.IExceptionWrapper;
import roberto.growth.process.common.exception.model.ExceptionDetailsInfo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈通用异常相关工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public class CommonExceptionUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 功能描述: <br>
     * 〈包装异常详细信息〉
     *
     * @param e
     * @return:roberto.growth.process.common.exception.model.ExceptionDetailsInfo
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 12:06
     */
    public static ExceptionDetailsInfo wrapperExceptionDetailsInfo(Throwable e) {
        ExceptionDetailsInfo exceptionDetailsInfo = new ExceptionDetailsInfo();
        if (e instanceof IExceptionWrapper) {
            exceptionDetailsInfo.setCode(((IExceptionWrapper) e).getCode());
            exceptionDetailsInfo.setExceptionOccurTime(simpleDateFormat.format(new Date(((IExceptionWrapper) e).getTimestamp())));
        } else {
            exceptionDetailsInfo.setExceptionOccurTime(simpleDateFormat.format(new Date()));
        }

        exceptionDetailsInfo.setExceptionClass(e.getClass().getName());
        exceptionDetailsInfo.setExceptionMessage(e.getLocalizedMessage());
        exceptionDetailsInfo.setExceptionFullStackTrace(getExceptionFullStackTrace(e));

        StackTraceElement elements[] = e.getStackTrace();
        exceptionDetailsInfo.setExceptionOccurClass(elements[0].getClassName());
        exceptionDetailsInfo.setExceptionOccurMethod(elements[0].getMethodName());
        exceptionDetailsInfo.setExceptionOccurLineNumber(Integer.toString(elements[0].getLineNumber()));

        Throwable rootCause = getRootCause(e);
        if (rootCause != null && rootCause != e) {
            StackTraceElement rootElements[] = rootCause.getStackTrace();
            exceptionDetailsInfo.setRootExceptionClass(rootCause.getClass().getName());
            exceptionDetailsInfo.setRootExceptionMessage(rootCause.getLocalizedMessage());
            exceptionDetailsInfo.setRootExceptionOccurClass(rootElements[0].getClassName());
            exceptionDetailsInfo.setRootExceptionOccurMethod(rootElements[0].getMethodName());
            exceptionDetailsInfo.setRootExceptionOccurLineNumber(Integer.toString(rootElements[0].getLineNumber()));
        }
        return exceptionDetailsInfo;
    }

    /**
     * 功能描述: <br>
     * 〈获取源异常信息〉
     *
     * @param e
     * @return:java.lang.Throwable
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 1:35
     */
    public static Throwable getRootCause(Throwable e) {
        Throwable rootCause = null;
        Throwable tempCause = e.getCause();
        while (tempCause != null && tempCause != rootCause) {
            rootCause = tempCause;
            tempCause = tempCause.getCause();
        }
        return rootCause == null ? e : rootCause;
    }

    /**
     * 功能描述: <br>
     * 〈获取异常堆栈信息〉
     *
     * @param e
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 5:33
     */
    public static String getExceptionFullStackTrace(Throwable e) {
        PrintWriter printWriter = null;
        try {
            StringWriter stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
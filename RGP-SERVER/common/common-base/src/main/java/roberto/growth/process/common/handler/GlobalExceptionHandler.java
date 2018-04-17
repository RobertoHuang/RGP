/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: GlobalExceptionHandler
 * Author:   HuangTaiHong
 * Date:     2018-02-23 下午 6:48
 * Description: 全局异常处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import roberto.growth.process.common.converter.message.CustomerCommonResponseFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈全局异常处理器〉
 *
 * @author HuangTaiHong
 * @create 2018-02-23
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public void handlerNoHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        commonExceptionHandler(request, response, HttpStatus.NOT_FOUND.value(), "请求的资源不存在", ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void handlerHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        commonExceptionHandler(request, response, HttpStatus.METHOD_NOT_ALLOWED.value(), "不支持的请求类型", ex);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void handlerHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        commonExceptionHandler(request, response, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "不支持的媒体类型", ex);
    }

    // 参数校验错误异常处理
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handMethodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException ex) {
        // 按需重新封装需要返回的错误信息
        List<ParamValidationResult> paramValidationResults = new ArrayList<>();
        // 解析原错误信息封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ParamValidationResult validationResult = new ParamValidationResult();
            validationResult.setParam(error.getField());
            validationResult.setMessage(error.getDefaultMessage());
            paramValidationResults.add(validationResult);
        }
        commonExceptionHandler(request, response, HttpStatus.BAD_REQUEST.value(), "请求的参数有误，请确认后重试", paramValidationResults, ex);
    }

    @ExceptionHandler(Exception.class)
    public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        commonExceptionHandler(request, response, HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误，请联系管理员", ex);
    }

    /**
     * 功能描述: <br>
     * 〈通用全局异常消息处理器〉
     *
     * @param request
     * @param response
     * @param retCode
     * @param friendlyMsg
     * @param ex
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/20 下午 7:19
     */
    public void commonExceptionHandler(HttpServletRequest request, HttpServletResponse response, Integer retCode, String friendlyMsg, Exception ex) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        String requestPath = request.getRequestURI();
        String exceptionName = ex.getClass().getName();
        String exceptionResponse = CustomerCommonResponseFormatter.formatWithException(requestPath, retCode, exceptionName, friendlyMsg);

        try {
            IOUtils.write(exceptionResponse, response.getWriter());
        } catch (IOException e) {
            log.error("全局异常响应处理器出错啦~", e);
        }
    }

    /**
     * 功能描述: <br>
     * 〈通用全局异常消息处理器〉
     *
     * @param request
     * @param response
     * @param retCode
     * @param friendlyMsg
     * @param errorMessages
     * @param ex
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/20 下午 7:20
     */
    public void commonExceptionHandler(HttpServletRequest request, HttpServletResponse response, Integer retCode, String friendlyMsg, Object errorMessages, Exception ex) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        String requestPath = request.getRequestURI();
        String exceptionName = ex.getClass().getName();
        String exceptionResponse = CustomerCommonResponseFormatter.formatWithException(requestPath, retCode, exceptionName, friendlyMsg, errorMessages);

        try {
            IOUtils.write(exceptionResponse, response.getWriter());
        } catch (IOException e) {
            log.error("全局异常响应处理器出错啦~", e);
        }
    }


    @Getter
    @Setter
    class ParamValidationResult {
        private String param;

        private String message;
    }
}
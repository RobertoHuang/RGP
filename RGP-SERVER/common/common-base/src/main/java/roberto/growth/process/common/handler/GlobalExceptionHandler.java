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
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import roberto.growth.process.common.response.RGPGenericResponse;
import roberto.growth.process.common.utils.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 保存异常与异常码异常信息对应关系
     **/
    private final static Map<Class, HttpStatus> exceptionCodeAndMessageMap = new HashMap<>();

    static {
        exceptionCodeAndMessageMap.put(NoHandlerFoundException.class, HttpStatus.NOT_FOUND);
        exceptionCodeAndMessageMap.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
        exceptionCodeAndMessageMap.put(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
        exceptionCodeAndMessageMap.put(HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseBody
    @ExceptionHandler(value = {
            Exception.class,
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class})
    public RGPGenericResponse handlerNoHandlerFoundException(HttpServletRequest request, Exception exception) {
        return this.wrapperExceptionResponse(request, exception);
    }

    // 参数校验错误异常处理
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RGPGenericResponse handMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        // 按需重新封装需要返回的错误信息
        List<ParamValidationResult> paramValidationResults = new ArrayList<>();
        // 解析原错误信息封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ParamValidationResult validationResult = new ParamValidationResult();
            validationResult.setParam(error.getField());
            validationResult.setMessage(error.getDefaultMessage());
            paramValidationResults.add(validationResult);
        }
        return this.wrapperExceptionResponse(request, exception,paramValidationResults);
    }

    /**
     * 功能描述: <br>
     * 〈封装异常响应信息〉
     *
     * @param request
     * @param exception
     * @return:roberto.growth.process.common.response.RGPGenericResponse
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/19 下午 4:34
     */
    public RGPGenericResponse wrapperExceptionResponse(HttpServletRequest request, Exception exception) {
        return this.wrapperExceptionResponse(request, exception, null);
    }

    /**
     * 功能描述: <br>
     * 〈封装异常响应信息〉
     *
     * @param request
     * @param exception
     * @param errorMessages
     * @return:roberto.growth.process.common.response.RGPGenericResponse
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/19 下午 4:33
     */
    public RGPGenericResponse wrapperExceptionResponse(HttpServletRequest request, Exception exception, Object errorMessages) {
        String requestPath = request.getRequestURI();
        String exceptionName = exception.getClass().getName();
        HttpStatus status = exceptionCodeAndMessageMap.get(exception.getClass());
        status = ObjectUtils.isEmpty(status) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        return new RGPGenericResponse(requestPath, status.value(), exceptionName, status.getReasonPhrase(), errorMessages);
    }


    @Getter
    @Setter
    class ParamValidationResult {
        private String param;

        private String message;
    }
}
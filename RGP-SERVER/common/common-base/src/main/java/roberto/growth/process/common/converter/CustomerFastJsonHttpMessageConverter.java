/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerMappingJacksonConverter
 * Author:   HuangTaiHong
 * Date:     2018-02-23 下午 3:14
 * Description: 自定义FastJson消息转换器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import roberto.growth.process.common.response.RGPGenericResponse;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义FastJSON消息转换器〉
 *
 * @author HuangTaiHong
 * @create 2018-02-23
 * @since 1.0.0
 */
public class CustomerFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(new RGPGenericResponse(HttpStatus.OK.value(), object), outputMessage);
    }
}
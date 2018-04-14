/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerStringHttpMessageConverter
 * Author:   HuangTaiHong
 * Date:     2018-02-23 下午 2:02
 * Description: 自定义String类型消息转换器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.converter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义String类型消息转换器〉
 *
 * @author HuangTaiHong
 * @create 2018-02-23
 * @since 1.0.0
 */
public class CustomerStringHttpMessageConverter extends StringHttpMessageConverter {
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    @Override
    protected Long getContentLength(String data, MediaType contentType) {
        Charset charset = getContentTypeCharset(contentType);
        try {
            String writeStr = CustomerCommonResponseFormatter.format(HttpStatus.OK.value(), data);
            return (long) writeStr.getBytes(charset.name()).length;
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        } else {
            return DEFAULT_CHARSET;
        }
    }
}
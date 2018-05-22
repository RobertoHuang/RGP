/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: FeignConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/22 23:49
 * Description: Feign配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.config;

import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Feign配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/22
 * @since 1.0.0
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder feignDecoder() {
        return new SpringDecoder(() -> new HttpMessageConverters()) {
            @Override
            public Object decode(Response response, Type type) throws IOException, FeignException {
                Response.Body body = response.body();
                JSONObject data = JSONObject.parseObject(body.toString()).getJSONObject("data");
                return super.decode(response.toBuilder().body(data.toJSONString(), Charset.defaultCharset()).build(), type);
            }
        };
    }
}
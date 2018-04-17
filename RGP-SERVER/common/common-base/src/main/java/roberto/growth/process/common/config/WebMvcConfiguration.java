/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WebConfig
 * Author:   HuangTaiHong
 * Date:     2018-02-23 下午 4:00
 * Description: SpringMVC配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import roberto.growth.process.common.converter.message.CustomerFastJsonHttpMessageConverter;
import roberto.growth.process.common.converter.message.CustomerStringHttpMessageConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈SpringMVC配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-23
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfiguration implements ApplicationContextAware, ResourceLoaderAware, WebMvcConfigurer {
    @Resource
    private ResourceProperties resourceProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置静态资源访问路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/");
    }

    @Bean
    public HttpMessageConverter<?> stringHttpMessageConverter() {
        return new CustomerStringHttpMessageConverter();
    }

    @Bean
    public HttpMessageConverter<?> fastJsonHttpMessageConverter() {
        CustomerFastJsonHttpMessageConverter converter = new CustomerFastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.parseMediaType("application/json;charset=utf-8"));
        supportedMediaTypes.add(MediaType.parseMediaType("text/plain;charset=utf-8"));
        supportedMediaTypes.add(MediaType.parseMediaType("application/*+json"));
        supportedMediaTypes.add(MediaType.parseMediaType("application/x-www-form-urlencoded"));
        converter.setSupportedMediaTypes(supportedMediaTypes);

        // 禁用FastJson循环依赖
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converter.setFastJsonConfig(fastJsonConfig);

        return converter;
    }

    /**
     * 功能描述: <br>
     * 〈配置SpringBoot 404时抛出异常〉
     *
     * @param resourceLoader
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/23 22:53
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        resourceProperties.setAddMappings(false);
    }

    /**
     * 功能描述: <br>
     * 〈配置SpringBoot 404时抛出异常〉
     *
     * @param applicationContext
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/23 22:52
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DispatcherServlet dispatcherServlet = applicationContext.getBean("dispatcherServlet", DispatcherServlet.class);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }
}
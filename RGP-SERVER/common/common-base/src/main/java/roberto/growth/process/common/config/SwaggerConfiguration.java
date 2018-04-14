/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SwaggerConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-03-23 下午 2:37
 * Description: Swagger文档服务配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * 〈一句话功能简述〉<br>
 * 〈Swagger文档服务配置〉
 *
 * @author HuangTaiHong
 * @create 2018-03-23
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * 文档服务基本信息
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 大标题
                .title("RGP系统 - API文档")
                // 当前版本
                .version("V1.0.0")
                // 服务条例
                .termsOfServiceUrl("#")
                // 许可信息
                .license("Roberto Growth Process版权所有")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").build();
    }

    /**
     * 配置文档生成规则
     **/
    @SuppressWarnings("all")
    private Predicate<RequestHandler> predicate() {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler requestHandler) {
                Class<?> declaringClass = requestHandler.declaringClass();
                String packageName = declaringClass.getPackage().getName();
                if (!packageName.startsWith("roberto.growth.process")) {
                    return false;
                } else {
                    return declaringClass.isAnnotationPresent(RestController.class) || requestHandler.isAnnotatedWith(ResponseBody.class) ? true : false;
                }
            }
        };
    }

    /**
     * 请求附带参数
     **/
    private List<ApiKey> securitySchemes() {
        // 请求认证
        ApiKey authorization = new ApiKey("Authorization", "Authorization", "header");
        return Arrays.asList(authorization);
    }

    /**
     * 认证配置
     **/
    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^.*$")).build();
        return Arrays.asList(securityContext);
    }

    /**
     * 认证配置
     **/
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("Global", "Access Everything"),
        };
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 文档基本信息
                .apiInfo(apiInfo())
                // 配置认证信息
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                // 将Date类型全部转为Long类型
                .directModelSubstitute(Date.class, Long.class)
                // 使用ResponseEntity通用模型替换
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                // 文档生成规则
                .apis(predicate()).build();
    }
}
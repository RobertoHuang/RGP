/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: JwtConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/22 0:20
 * Description: JWT配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.acd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈JWT配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/22
 * @since 1.0.0
 */
@Configuration
public class JwtConfiguration {
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        try {
            String publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
            converter.setVerifierKey(publicKey);
            return converter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
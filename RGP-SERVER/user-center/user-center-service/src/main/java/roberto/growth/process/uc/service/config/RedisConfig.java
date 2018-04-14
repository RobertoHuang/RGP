/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisConfig
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 8:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roberto.growth.process.common.config.properties.RedisPoolProperties;
import roberto.growth.process.common.config.properties.RedisStandaloneProperties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13 
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisPoolProperties redisPoolProperties() {
        return new RedisPoolProperties();
    }

    @Bean
    public RedisStandaloneProperties redisStandaloneProperties() {
        RedisStandaloneProperties redisStandaloneProperties = new RedisStandaloneProperties();
        redisStandaloneProperties.setHostname("192.168.56.128");
        return redisStandaloneProperties;
    }
}
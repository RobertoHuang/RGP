package roberto.growth.process.security.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roberto.growth.process.common.config.properties.RedisPoolProperties;
import roberto.growth.process.common.config.properties.RedisStandaloneProperties;

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
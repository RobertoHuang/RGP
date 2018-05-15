/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-04-12 下午 4:03
 * Description: Redis缓存配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import roberto.growth.process.common.config.properties.RedisPoolProperties;
import roberto.growth.process.common.config.properties.RedisStandaloneProperties;
import roberto.growth.process.common.utils.SystemPropertiesUtils;

import java.time.Duration;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis缓存配置〉
 * <p>
 * Lettuce是可伸缩线程安全的Redis客户端
 * 多个线程可以共享同一个RedisConnection
 * 它利用优秀netty NIO框架来高效地管理多个连接
 *
 * @author HuangTaiHong
 * @create 2018-04-12
 * @since 1.0.0
 */
@EnableCaching
@Configuration
public class RedisConfiguration {
    @Autowired(required = false)
    private RedisPoolProperties redisPoolProperties;

    @Autowired(required = false)
    private RedisStandaloneProperties redisStandaloneProperties;

    @Bean
    @ConditionalOnBean(RedisPoolProperties.class)
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(redisPoolProperties.getMaxTotal());
        // 最大空闲连接数
        poolConfig.setMaxIdle(redisPoolProperties.getMaxIdle());
        // 最小空闲连接数
        poolConfig.setMinIdle(redisPoolProperties.getMinIdle());
        // 连接时最大等待毫秒数
        poolConfig.setMaxWaitMillis(redisPoolProperties.getMaxWaitMillis());
        // 连接超时时是否进行阻塞
        poolConfig.setBlockWhenExhausted(redisPoolProperties.getBlockWhenExhausted());
        // 在获取连接的时候检查有效性
        poolConfig.setTestOnBorrow(redisPoolProperties.getTestOnBorrow());
        // 在返回连接的时候检查有效性
        poolConfig.setTestOnReturn(redisPoolProperties.getTestOnReturn());
        // 定时对线程池中空闲链接进行validateObject校验
        poolConfig.setTestWhileIdle(redisPoolProperties.getTestWhileIdle());
        // 每次逐出检查时 逐出的最大数目
        poolConfig.setNumTestsPerEvictionRun(redisPoolProperties.getNumTestsPerEvictionRun());
        // 逐出连接的最小空闲时间
        poolConfig.setMinEvictableIdleTimeMillis(redisPoolProperties.getMinEvictableIdleTimeMillis());
        // 对象空闲多久后逐出 当空闲时间>该值 且 空闲连接>最大空闲数时直接逐出 不用根据MinEvictableIdleTimeMillis的值来判断
        poolConfig.setSoftMinEvictableIdleTimeMillis(redisPoolProperties.getSoftMinEvictableIdleTimeMillis());
        // 逐出策略 默认为org.apache.commons.pool2.impl.DefaultEvictionPolicy
        poolConfig.setEvictionPolicyClassName(redisPoolProperties.getEvictionPolicyClassName());
        return poolConfig;
    }

    @Bean
    @ConditionalOnBean(RedisStandaloneProperties.class)
    public LettuceConnectionFactory standaloneLettuceConnectionFactory() {
        // 配置Redis服务属性
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisStandaloneProperties.getHostname());
        redisStandaloneConfiguration.setPort(redisStandaloneProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisStandaloneProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(redisStandaloneProperties.getDatabase());

        // 配置Redis连接属性
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(redisStandaloneProperties.getCommandTimeout())
                .shutdownTimeout(redisStandaloneProperties.getShutdownTimeout())
                .poolConfig(jedisPoolConfig()).build();

        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
    }

    @Bean
    @ConditionalOnBean(LettuceConnectionFactory.class)
    public ReactiveRedisTemplate reactiveRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisSerializationContext redisSerializationContext = RedisSerializationContext.<String, Object>newSerializationContext()
                .key(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .hashKey(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .value(RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()))
                .hashValue(RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer())).build();
        return new ReactiveRedisTemplate(lettuceConnectionFactory, redisSerializationContext);
    }

    @Bean
    @ConditionalOnBean(LettuceConnectionFactory.class)
    public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        // 获取对应项目名称
        String projectName = SystemPropertiesUtils.getInstance().getProjectName();

        // 缓存相关配置
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ZERO)
                .disableCachingNullValues()
                .computePrefixWith(cacheName -> "RGP:".concat(projectName).concat(":").concat(cacheName))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()))
                .withConversionService(new DefaultConversionService());

        return RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(cacheConfiguration).build();
    }
}
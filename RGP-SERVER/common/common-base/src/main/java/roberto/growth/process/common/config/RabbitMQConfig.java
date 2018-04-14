/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RabbitMQConfig
 * Author:   HuangTaiHong
 * Date:     2018-03-01 下午 5:49
 * Description: RabbitMQ相关配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈RabbitMQ相关配置〉
 *
 * @author HuangTaiHong
 * @create 2018-03-01
 * @since 1.0.0
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("192.168.56.128");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }
}
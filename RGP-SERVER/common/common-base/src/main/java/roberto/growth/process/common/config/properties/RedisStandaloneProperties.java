/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisStandaloneProperties
 * Author:   HuangTaiHong
 * Date:     2018-04-12 下午 4:50
 * Description: Redis缓存配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.connection.RedisPassword;

import java.time.Duration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Redis缓存单实例配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-12 
 * @since 1.0.0
 */
@Getter
@Setter
public class RedisStandaloneProperties {
    /**
     * 主机名
     **/
    private String hostname;

    /**
     * 端口号 默认端口号6379
     **/
    private Integer port = 6379;

    /**
     * 密码 默认无需密码
     **/
    private RedisPassword password = RedisPassword.none();

    /**
     * 数据库 默认0号数据库
     **/
    private Integer database = 0;

    /**
     * 执行命令超时时间 默认为3S
     **/
    private Duration commandTimeout = Duration.ofSeconds(3);

    /**
     * 连接关闭超时时间 默认为3S
     **/
    private Duration shutdownTimeout = Duration.ofSeconds(3);
}
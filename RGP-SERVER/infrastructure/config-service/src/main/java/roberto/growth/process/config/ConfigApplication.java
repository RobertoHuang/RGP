/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ConfigApplication
 * Author:   HuangTaiHong
 * Date:     2018-04-20 下午 6:45
 * Description: 配置中心启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置中心启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-20
 * @since 1.0.0
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
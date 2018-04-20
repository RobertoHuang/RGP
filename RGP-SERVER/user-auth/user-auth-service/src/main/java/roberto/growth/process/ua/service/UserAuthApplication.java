/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserAuthApplication
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 6:51
 * Description: User Auth服务启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.ua.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈User Auth服务启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18
 * @since 1.0.0
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "roberto.growth.process")
public class UserAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthApplication.class, args);
    }
}
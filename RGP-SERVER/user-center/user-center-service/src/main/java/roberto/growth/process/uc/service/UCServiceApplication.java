/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UCServiceApplication
 * Author:   HuangTaiHong
 * Date:     2018-04-11 下午 7:44
 * Description: UC Service服务启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈UC Service服务启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-11
 * @since 1.0.0
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "roberto.growth.process")
public class UCServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCServiceApplication.class, args);
    }
}
package com.roberto.growth.process.eureka; /**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: com.roberto.growth.process.eureka.EurekaServiceApplication
 * Author:   HuangTaiHong
 * Date:     2018-04-10 下午 6:59
 * Description: Eureka服务启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Eureka服务启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-10 
 * @since 1.0.0
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApplication.class, args);
    }
}
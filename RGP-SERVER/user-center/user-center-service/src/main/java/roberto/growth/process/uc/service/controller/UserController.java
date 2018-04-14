/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserController
 * Author:   HuangTaiHong
 * Date:     2018-04-11 下午 7:46
 * Description: 用户Controller层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.common.service.service.RedisService;
import roberto.growth.process.uc.service.service.UserService;

import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户Controller层〉
 *
 * @author HuangTaiHong
 * @create 2018-04-11
 * @since 1.0.0
 */
@EnableCaching
@RestController
public class UserController {
    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    private RedisService<String> redisService;

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String helloWorld() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("123", "456");
        hashMap.put("567", "789");
        redisService.setCacheMap("000000", hashMap);
        System.out.println(redisService.getCacheMap("000000"));
        return "HELLO WORLD";
    }
}
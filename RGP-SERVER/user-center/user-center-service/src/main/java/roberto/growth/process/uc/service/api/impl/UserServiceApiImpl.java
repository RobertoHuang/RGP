/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserServiceApiImpl
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 7:51
 * Description: 用户服务API实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.api.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.common.generator.impl.TwitterSnowflakeGenerator;
import roberto.growth.process.uc.api.api.UserServiceApi;
import roberto.growth.process.uc.api.vo.request.SaveUserRequest;
import roberto.growth.process.uc.api.vo.response.SaveUserResponse;
import roberto.growth.process.uc.service.entity.User;
import roberto.growth.process.uc.service.service.UserService;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户服务API实现类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserServiceApiImpl implements UserServiceApi {
    @Autowired
    private UserService userService;

    @Autowired
    private TwitterSnowflakeGenerator twitterSnowflakeGenerator;

    @Override
    public SaveUserResponse saveUser(SaveUserRequest saveUserRequest) {
        // 保存用户信息
        User user = new User();
        BeanUtils.copyProperties(saveUserRequest, user);
        user.setId(twitterSnowflakeGenerator.generate());
        user.setCreateBy(saveUserRequest.getUsername());
        user.setCreateDate(new Date());
        user.setUpdateBy(saveUserRequest.getUsername());
        user.setUpdateDate(new Date());
        user = userService.saveUser(user);

        // 返回响应数据
        SaveUserResponse saveUserResponse = new SaveUserResponse();
        BeanUtils.copyProperties(user, saveUserResponse);
        return saveUserResponse;
    }

    @RequestMapping(value = "/hello")
    public String sayHello() {
        return "HELLO WORLD";
    }
}
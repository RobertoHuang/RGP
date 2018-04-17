/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserServiceImpl
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 3:33
 * Description: 用户Service层实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roberto.growth.process.uc.service.entity.User;
import roberto.growth.process.uc.service.repository.UserRepository;
import roberto.growth.process.uc.service.service.UserService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户Service层实现〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13 
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
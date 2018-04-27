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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import roberto.growth.process.common.utils.ObjectUtils;
import roberto.growth.process.uc.api.enums.RGPUserCenterExceptionCodeEnum;
import roberto.growth.process.uc.api.exception.RGPUserCenterException;
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
@EnableCaching
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "USER_", key = "#user.id")
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User validateUser(String username, String password) throws RGPUserCenterException {
        User user = userRepository.findUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new RGPUserCenterException(RGPUserCenterExceptionCodeEnum.USERNAME_NOT_FOUNT);
        } else {
            if (!user.getPassword().equals(password)) {
                throw new RGPUserCenterException(RGPUserCenterExceptionCodeEnum.USERNAME_PASSWORD_NOT_MATCH);
            } else {
                return user;
            }
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws RGPUserCenterException {
        User user = userRepository.findUserByPhoneNumber(phoneNumber);
        if (ObjectUtils.isNotEmpty(user)) {
            return user;
        } else {
            throw new RGPUserCenterException(RGPUserCenterExceptionCodeEnum.PHONE_NUMBER_UNBOUND_ACCOUNT);
        }
    }

    @Override
    @Cacheable(value = "USER_", key = "#userId")
    public User getUserInfo(String userId) {
        return userRepository.getOne(userId);
    }
}
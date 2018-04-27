/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserService
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 3:32
 * Description: 用户Service层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.service;

import roberto.growth.process.uc.api.exception.RGPUserCenterException;
import roberto.growth.process.uc.service.entity.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户Service层接口〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13 
 * @since 1.0.0
 */
public interface UserService {
    /**
     * 功能描述: <br>
     * 〈保存用户〉
     *
     * @param user
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/16 下午 8:16
     */
    User saveUser(User user);

    /**
     * 功能描述: <br>
     * 〈通过用户名和密码校验用户信息〉
     *
     * @param username
     * @param password
     * @return:roberto.growth.process.uc.service.entity.User
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/18 上午 11:58
     */
    User validateUser(String username,String password) throws RGPUserCenterException;

    /**
     * 功能描述: <br>
     * 〈通过手机号码查找用户信息〉
     *
     * @param phoneNumber
     * @return roberto.growth.process.uc.service.entity.User
     * @throws RGPUserCenterException
     * @author HuangTaiHong
     * @date 2018.04.26 14:42:08
     */
    User getUserByPhoneNumber(String phoneNumber) throws RGPUserCenterException;

    User getUserInfo(String userId);
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserRepository
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 8:13
 * Description: 用户Repositoty层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roberto.growth.process.uc.service.entity.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户Dao层接口〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 功能描述: <br>
     * 〈通过用户名查找用户信息〉
     *
     * @param username
     * @return roberto.growth.process.uc.service.entity.User
     * @author HuangTaiHong
     * @date 2018.04.26 14:43:33
     */
    User findUserByUsername(String username);

    /**
     * 功能描述: <br>
     * 〈通过手机号码查找用户〉
     *
     * @param phoneNumber
     * @return roberto.growth.process.uc.service.entity.User
     * @author HuangTaiHong
     * @date 2018.04.26 14:43:07
     */
    User findUserByPhoneNumber(String phoneNumber);
}
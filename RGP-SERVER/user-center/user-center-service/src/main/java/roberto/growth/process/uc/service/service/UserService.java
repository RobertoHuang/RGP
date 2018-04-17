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
}
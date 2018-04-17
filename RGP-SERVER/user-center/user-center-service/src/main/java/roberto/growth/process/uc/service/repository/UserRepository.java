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

}
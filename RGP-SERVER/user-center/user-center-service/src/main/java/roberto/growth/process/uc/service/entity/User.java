/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: User
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 7:55
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.entity;

import lombok.Getter;
import lombok.Setter;
import roberto.growth.process.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户实体类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16 
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User extends BaseEntity{
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: User
 * Author:   HuangTaiHong
 * Date:     2018-02-14 上午 10:30
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.webapp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 〈一句话功能简述〉<br> 
 * 〈用户实体类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-14
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体类")
public class User {
    @Email
    @NotBlank
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密  码")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
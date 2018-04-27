/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserDetail
 * Author:   HuangTaiHong
 * Date:     2018-04-18 上午 11:31
 * Description: 用户详细信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.vo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户详细信息〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18 
 * @since 1.0.0
 */
@Getter
@Setter
public class UserDetail implements Serializable{
    private static final long serialVersionUID = -6841000755438586234L;

    private Long id;

    private String username;

    private String phoneNumber;

    private String emailAddress;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
}
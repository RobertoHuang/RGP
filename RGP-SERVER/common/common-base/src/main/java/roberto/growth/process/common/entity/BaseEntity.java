/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: BaseEntity
 * Author:   HuangTaiHong
 * Date:     2018-04-04 下午 12:01
 * Description: 实体类基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实体类基类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-04 
 * @since 1.0.0
 */
@Getter
@Setter
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = -1999912863684532509L;

    /**
     * 主键
     **/
    protected String id;

    /**
     * 创建时间
     **/
    protected Date createDate;

    /**
     * 创建者
     **/
    protected String createBy;

    /**
     * 修改时间
     **/
    protected Date updateDate;

    /**
     * 修改人
     **/
    protected String updateBy;
}
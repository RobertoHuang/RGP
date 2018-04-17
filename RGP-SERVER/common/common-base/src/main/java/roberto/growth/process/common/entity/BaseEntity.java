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
import roberto.growth.process.common.converter.database.DateConverter;

import javax.persistence.*;
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
@MappedSuperclass
public abstract class BaseEntity {
    /**
     * 主键
     **/
    @Id
    protected Long id;

    /**
     * 创建时间
     **/
    @Column(name = "create_date")
    @Convert(converter = DateConverter.class)
    protected Date createDate;

    /**
     * 创建者
     **/
    @Column(name = "create_by")
    protected String createBy;

    /**
     * 修改时间
     **/
    @Column(name = "update_date")
    @Convert(converter = DateConverter.class)
    protected Date updateDate;

    /**
     * 修改人
     **/
    @Column(name = "update_by")
    protected String updateBy;
}
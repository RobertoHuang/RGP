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
    @Id
    protected Long id;

    /** 创建者 **/
    @Column(name = "creator")
    protected String creator;

    /** 创建时间 **/
    @Column(name = "create_time")
    @Convert(converter = DateConverter.class)
    protected Date createTime;

    /** 修改人 **/
    @Column(name = "updater")
    protected String updater;

    /** 修改时间 **/
    @Column(name = "update_time")
    @Convert(converter = DateConverter.class)
    protected Date updateTime;
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: DateConverter
 * Author:   HuangTaiHong
 * Date:     2018-04-17 上午 10:25
 * Description: 数据库时间格式转换器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.converter.database;

import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据库时间格式转换器〉
 *
 * @author HuangTaiHong
 * @create 2018-04-17
 * @since 1.0.0
 */
public class DateConverter implements AttributeConverter<Date, Long> {
    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        if (!ObjectUtils.isEmpty(attribute)) {
            return attribute.getTime();
        }
        return null;
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        if (!ObjectUtils.isEmpty(dbData)) {
            return new Date(dbData);
        }
        return null;
    }
}
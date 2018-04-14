/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: DatabaseUtils
 * Author:   HuangTaiHong
 * Date:     2018-04-03 下午 2:24
 * Description: 数据库工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.utils;

import roberto.growth.process.common.exception.RGPBaseRTException;
import roberto.growth.process.common.exception.enums.RGPBaseExceptionCodeEnum;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据库工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-03
 * @since 1.0.0
 */
public class DatabaseUtils {
    /**
     * 功能描述: <br>
     * 〈判断数据库表是否存在〉
     *
     * @param dataSource
     * @param tableName
     * @return:boolean
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/3 下午 2:47
     */
    public static boolean isTableExist(DataSource dataSource, String tableName) {
        try {
            Connection connection = dataSource.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            return databaseMetaData.getTables(null, null, tableName, null).next();
        } catch (SQLException e) {
            throw new RGPBaseRTException(RGPBaseExceptionCodeEnum.DATABASE_CONNECTION_ERROR, e);
        }
    }
}
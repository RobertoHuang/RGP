/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SystemPropertiesUtils
 * Author:   HuangTaiHong
 * Date:     2018-02-24 下午 4:24
 * Description: 系统属性相关工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈系统属性相关工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-24
 * @since 1.0.0
 */
public class SystemPropertiesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemPropertiesUtils.class);

    private Properties systemProperties;
    private static SystemPropertiesUtils instance;

    private static String PROJECT_NAME = "projectName";
    private static String SYSTEM_PROPERTIES = "system.properties";

    private SystemPropertiesUtils() {
        init(SYSTEM_PROPERTIES);
    }

    public static SystemPropertiesUtils getInstance() {
        if (instance == null) {
            synchronized (SystemPropertiesUtils.class) {
                if (instance == null) {
                    instance = new SystemPropertiesUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 功能描述: <br>
     * 〈读取系统配置文件初始化Properties〉
     *
     * @param propFile
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/24 下午 7:26
     */
    private void init(String propFile) {
        if (systemProperties == null) {
            URL url = Thread.currentThread().getContextClassLoader().getResource(propFile);
            if (url == null) {
                LOGGER.debug(new StringBuffer().append("尝试读取系统配置文件:").append(SYSTEM_PROPERTIES).append(",但是文件不存在!").toString());
                return;
            } else {
                try {
                    LOGGER.debug(new StringBuffer().append("正在尝试读取系统配置文件:").append(SYSTEM_PROPERTIES).toString());
                    InputStream inputStream = url.openStream();
                    systemProperties = new Properties();
                    systemProperties.load(inputStream);
                    LOGGER.debug(new StringBuffer().append("读取系统配置文件成功:").append(SYSTEM_PROPERTIES).toString());
                } catch (Exception e) {
                    LOGGER.error(new StringBuffer().append("读取系统配置文件失败:").append(SYSTEM_PROPERTIES).append(" 文件URL地址为:").append(url.toExternalForm()).toString(), e);
                }
            }
        }
    }

    /**
     * 功能描述: <br>
     * 〈获取当前工程名称〉
     *
     * @param
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/24 下午 7:33
     */
    public String getProjectName() {
        return getSystemProperty(PROJECT_NAME);
    }

    /**
     * 功能描述: <br>
     * 〈根据KEY获取配置参数〉
     *
     * @param key
     * @return
     * @since [1.0.0](可选)
     */
    public String getSystemProperty(String key) {
        if (systemProperties == null) {
            return System.getProperty(key);
        } else {
            String value = systemProperties.getProperty(key);
            if (value == null) {
                return System.getProperty(key);
            } else {
                return value;
            }
        }
    }
}
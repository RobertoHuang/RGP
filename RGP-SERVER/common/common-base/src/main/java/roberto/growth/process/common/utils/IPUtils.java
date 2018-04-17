/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IPUtils
 * Author:   HuangTaiHong
 * Date:     2018-04-17 下午 7:43
 * Description: IP地址相关工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.utils;

import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 〈一句话功能简述〉<br>
 * 〈IP地址相关工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-17
 * @since 1.0.0
 */
public class IPUtils {
    /**
     * 功能描述: <br>
     * 〈获取本机IP〉
     *
     * @param
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/17 下午 7:47
     */
    public static String getLocalIpAddress() throws SocketException {
        // 本地IP
        String localIp = null;
        // 外网IP
        String networkIp = null;

        InetAddress ip;
        // 是否找到外网IP
        boolean finded = false;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    // 外网IP
                    networkIp = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    // 内网IP
                    localIp = ip.getHostAddress();
                }
            }
        }

        return ObjectUtils.isEmpty(networkIp) ? localIp : networkIp;
    }
}
/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisPoolProperties
 * Author:   HuangTaiHong
 * Date:     2018-04-12 下午 9:31
 * Description: Redis连接池配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis连接池配置属性〉
 *
 *  官方提供非常多的连接池配置属性
 *  RGP项目只提供部分属性 后续有特殊需要再补充
 *
 * @author HuangTaiHong
 * @create 2018-04-12
 * @since 1.0.0
 */
@Getter
@Setter
public class RedisPoolProperties {
    /**
     * 最大连接数 默认为8
     **/
    private Integer maxTotal = 8;

    /**
     * 最大空闲连接数 默认为8
     **/
    private Integer maxIdle = 8;

    /**
     * 最小空闲连接数 默认为0
     **/
    private Integer minIdle = 0;

    /**
     * 获取连接时最大等待毫秒数
     * 小于0表示阻塞不确定时间 默认为-1
     * 如果设置为阻塞时BlockWhenExhausted 如果超时就抛异常
     **/
    private Integer maxWaitMillis = -1;

    /**
     * 连接超时时是否阻塞
     *
     * false报异常 true阻塞直到超时
     **/
    private Boolean blockWhenExhausted = true;

    /**
     * 在获取连接的时候检查有效性 默认为false
     **/
    private Boolean testOnBorrow = false;

    /**
     * 在返回连接的时候检查有效性 默认为false
     **/
    private Boolean testOnReturn = false;

    /**
     * 定时对线程池中空闲链接进行validateObject校验
     **/
    private Boolean testWhileIdle = true;

    /**
     * 每次逐出检查时 逐出的最大数目
     * 如果是负数就是1/abs(n),默认是3
     **/
    private Integer numTestsPerEvictionRun = 3;

    /**
     * 配置逐出连接的最小空闲时间 默认为30分钟
     **/
    private Integer minEvictableIdleTimeMillis = 1800000;

    /**
     * 对象空闲多久后逐出
     * 当空闲时间>该值 且 空闲连接>最大空闲数时直接逐出
     * 不用根据MinEvictableIdleTimeMillis的值来判断
     **/
    private Integer softMinEvictableIdleTimeMillis = 1800000;

    /**
     * 逐出策略（默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
     **/
    private String evictionPolicyClassName = "org.apache.commons.pool2.impl.DefaultEvictionPolicy";
}
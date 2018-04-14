/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisService
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 4:21
 * Description: Redis Service层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.service.service;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Redis Service层接口〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13 
 * @since 1.0.0
 */
public interface RedisService<T>{
    /**
     * 功能描述: <br>
     * 〈删除缓存〉
     *
     * @param key
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:24
     */
    void deleteCacheObject(String key);

    /**
     * 功能描述: <br>
     * 〈删除集合对象〉
     *
     * @param collection
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:25
     */
    void deleteCacheCollection(Collection collection);

    /**
     * 功能描述: <br>
     * 〈获取基本对象〉
     *
     * @param key
     * @return:T
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:24
     */
    <T> T getCacheObject(String key);

    /**
     * 功能描述: <br>
     * 〈缓存基本对象〉
     *
     * @param key
     * @param value
     * @return:org.springframework.data.redis.core.ValueOperations<java.lang.String,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:22
     */
    <T> Boolean setCacheObject(String key, T value);

    /**
     * 功能描述: <br>
     * 〈缓存基本对象 带过期时间〉
     *
     * @param key
     * @param value
     * @param timeout
     * @return:java.lang.Boolean
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 7:25
     */
    <T> Boolean setCacheObject(String key, T value, Duration timeout);

    /**
     * 功能描述: <br>
     * 〈获取List数据〉
     *
     * @param key
     * @return:java.util.List<T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:35
     */
    <T> List<T> getCacheList(String key);

    /**
     * 功能描述: <br>
     * 〈缓存List数据〉
     *
     * @param key
     * @param dataList
     * @return:org.springframework.data.redis.core.ListOperations<java.lang.String,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:26
     */
    <T> Boolean setCacheList(String key, List<T> dataList);

    /**
     * 功能描述: <br>
     * 〈获取Set数据〉
     *
     * @param key
     * @return:java.util.Set<T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:35
     */
    <T> Set<T> getCacheSet(String key);

    /**
     * 功能描述: <br>
     * 〈缓存Set数据〉
     *
     * @param key
     * @param dataSet
     * @return:org.springframework.data.redis.core.BoundSetOperations<java.lang.String,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:40
     */
    <T> Boolean setCacheSet(String key, Set<T> dataSet);

    /**
     * 功能描述: <br>
     * 〈获取Map数据〉
     *
     * @param key
     * @return:java.util.Map<java.lang.String,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:46
     */
    <T> Map<String, T> getCacheMap(String key);

    /**
     * 功能描述: <br>
     * 〈缓存Map数据〉
     *
     * @param key
     * @param dataMap
     * @return:org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:47
     */
    <T> Boolean setCacheMap(String key, Map<String, T> dataMap);

    /**
     * 功能描述: <br>
     * 〈获取Map数据〉
     *
     * @param key
     * @return:java.util.Map<java.lang.Integer,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:48
     */
    <T> Map<Integer, T> getCacheIntegerMap(String key);

    /**
     * 功能描述: <br>
     * 〈缓存Map数据〉
     *
     * @param key
     * @param dataMap
     * @return:org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.Integer,T>
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/13 下午 4:48
     */
    <T> Boolean setCacheIntegerMap(String key, Map<Integer, T> dataMap);
}
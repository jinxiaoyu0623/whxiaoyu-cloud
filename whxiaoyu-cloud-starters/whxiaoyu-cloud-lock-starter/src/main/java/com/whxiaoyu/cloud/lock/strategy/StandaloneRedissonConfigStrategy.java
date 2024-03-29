package com.whxiaoyu.cloud.lock.strategy;

import cn.hutool.core.util.StrUtil;
import com.whxiaoyu.cloud.lock.config.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;


/**
 * 单机方式Redisson配置
 *
 * @author jinxiaoyu
 */
@Slf4j
public class StandaloneRedissonConfigStrategy implements RedissonConfigStrategy {

    @Override
    public Config config(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String redisAddr = REDIS_CONNECTION_PREFIX + address;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            if (!StrUtil.isEmpty(password)) {
                config.useSingleServer().setPassword(password);
            }
        } catch (Exception e) {
            log.error("单机Redisson初始化错误", e);
        }
        return config;
    }
}

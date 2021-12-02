package com.whxiaoyu.component.lock.strategy;


import com.whxiaoyu.component.lock.RedissonProperties;
import org.redisson.config.Config;

/**
 * Redisson 配置接口
 *
 * @author jinxiaoyu
 */
public interface RedissonConfigStrategy {

	/**
	 * Redis地址连接前缀
	 */
	String REDIS_CONNECTION_PREFIX = "redis://";

	/**
	 * 不同连接方式生成配置
	 * @param redissonProperties 配置参数
	 * @return config
	 */
	Config config(RedissonProperties redissonProperties);
}

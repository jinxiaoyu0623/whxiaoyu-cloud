package com.whxiaoyu.cloud.lock.config;


import com.whxiaoyu.cloud.lock.aspect.JLockAspect;
import com.whxiaoyu.cloud.lock.aspect.JRepeatAspect;
import com.whxiaoyu.cloud.lock.strategy.*;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * Redisson自动化配置
 *
 * @author jinxiaoyu
 */
@Configuration
@ConditionalOnClass(RedissonProperties.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {


	@Bean
	@ConditionalOnMissingBean
	public RedissonClient redissonClient(RedissonProperties redissonProperties) {
		return Redisson.create(createConfig(redissonProperties));
	}


	@Bean
	@ConditionalOnBean(RedissonClient.class)
	public JRepeatAspect repeatAspect(RedissonClient redissonClient) {
		return new JRepeatAspect(redissonClient);
	}


	@Bean
	@ConditionalOnBean(RedissonClient.class)
	public JLockAspect lockAspect(RedissonClient redissonClient) {
		return new JLockAspect(redissonClient);
	}

	/**
	 * 根据连接类型創建连接方式的配置
	 *
	 * @param redissonProperties 配置
	 * @return Config
	 */
	private Config createConfig(RedissonProperties redissonProperties) {
		Objects.requireNonNull(redissonProperties.getAddress(),"redission address not null");
		RedissonConnectionType connectionType = redissonProperties.getType();
		// 声明连接方式
		RedissonConfigStrategy redissonConfigStrategy;
		if (connectionType.equals(RedissonConnectionType.SENTINEL)) {
			redissonConfigStrategy = new SentinelRedissonConfigStrategy();
		} else if (connectionType.equals(RedissonConnectionType.CLUSTER)) {
			redissonConfigStrategy = new ClusterRedissonConfigStrategy();
		} else if (connectionType.equals(RedissonConnectionType.MASTERSLAVE)) {
			redissonConfigStrategy = new MasterSlaveRedissonConfigStrategy();
		} else {
			redissonConfigStrategy = new StandaloneRedissonConfigStrategy();
		}
		return redissonConfigStrategy.config(redissonProperties);
	}

}

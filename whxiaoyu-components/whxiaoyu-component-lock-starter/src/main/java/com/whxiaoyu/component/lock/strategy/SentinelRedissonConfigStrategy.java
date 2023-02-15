package com.whxiaoyu.component.lock.strategy;

import cn.hutool.core.util.StrUtil;
import com.whxiaoyu.component.lock.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;


/**
 * 哨兵方式Redis连接配置
 * 比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name
 * 配置方式:my-sentinel-name,127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399]
 *
 * @author jinxiaoyu
 */
@Slf4j
public class SentinelRedissonConfigStrategy implements RedissonConfigStrategy {

	@Override
	public Config config(RedissonProperties redissonProperties) {
		Config config = new Config();
		try {
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			int database = redissonProperties.getDatabase();
			String[] addrs = address.split(",");
			String sentinelAliasName = addrs[0];
			// 设置redis配置文件sentinel.conf配置的sentinel别名
			config.useSentinelServers().setMasterName(sentinelAliasName);
			config.useSentinelServers().setDatabase(database);
			if (!StrUtil.isEmpty(password)) {
				config.useSentinelServers().setPassword(password);
			}
			// 设置哨兵节点的服务IP和端口
			for (String addr : addrs) {
				config.useSentinelServers().addSentinelAddress(REDIS_CONNECTION_PREFIX+ addr);
			}
		} catch (Exception e) {
			log.error("哨兵Redisson初始化错误", e);
		}
		return config;
	}
}

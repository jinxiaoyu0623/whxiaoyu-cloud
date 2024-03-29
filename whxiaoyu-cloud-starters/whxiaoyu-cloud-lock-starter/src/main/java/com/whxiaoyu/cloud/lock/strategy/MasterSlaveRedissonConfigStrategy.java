package com.whxiaoyu.cloud.lock.strategy;


import cn.hutool.core.util.StrUtil;
import com.whxiaoyu.cloud.lock.config.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 主从方式Redisson配置
 * <p>配置方式: 127.0.0.1:6379(主),127.0.0.1:6380(子),127.0.0.1:6381(子)</p>
 *
 * @author jinxiaoyu
 *
 */
@Slf4j
public class MasterSlaveRedissonConfigStrategy implements RedissonConfigStrategy {

	@Override
	public Config config(RedissonProperties redissonProperties) {
		Config config = new Config();
		try {
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			int database = redissonProperties.getDatabase();
			String[] addrs = address.split(",");
			String masterNodeAddr = addrs[0];
			// 设置主节点ip
			config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
			if (!StrUtil.isEmpty(password)) {
				config.useMasterSlaveServers().setPassword(password);
			}
			config.useMasterSlaveServers().setDatabase(database);
			// 设置从节点，移除第一个节点，默认第一个为主节点
			List<String> slaveList = new ArrayList<>();
			for (String addr : addrs) {
				slaveList.add(REDIS_CONNECTION_PREFIX + addr);
			}
			slaveList.remove(0);
			int size = slaveList.size();
			config.useMasterSlaveServers().addSlaveAddress(slaveList.toArray(new String[size]));
		} catch (Exception e) {
			log.error("主从Redisson初始化错误", e);
		}
		return config;
	}
}

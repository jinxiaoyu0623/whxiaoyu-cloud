package com.whxiaoyu.component.lock.strategy;

import cn.hutool.core.util.StrUtil;
import com.whxiaoyu.component.lock.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;

/**
 * 集群配置
 *
 * @author jinxiaoyu
 */
@Slf4j
public class ClusterRedissonConfigStrategy implements RedissonConfigStrategy {

    @Override
    public Config config(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            String[] addrs = address.split(",");
            // 设置集群(cluster)节点的服务IP和端口
            for (String addr : addrs) {
                config.useClusterServers().addNodeAddress(REDIS_CONNECTION_PREFIX + addr);
                if (!StrUtil.isEmpty(password)) {
                    config.useClusterServers().setPassword(password);
                }
            }
        } catch (Exception e) {
            log.error("集群Redisson初始化错误", e);
        }
        return config;
    }
}

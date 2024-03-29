package com.whxiaoyu.cloud.lock.config;

import com.whxiaoyu.cloud.lock.config.RedissonConnectionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jinxiaoyu
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonProperties {

    private final Integer DEFAULT_DATABASE = 0;

    /**
     * 连接方式
     */
    private RedissonConnectionType type;

    /**
     * redis服务器地址 ip:port
     */
    private String address;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库
     */
    private Integer database = DEFAULT_DATABASE;

}

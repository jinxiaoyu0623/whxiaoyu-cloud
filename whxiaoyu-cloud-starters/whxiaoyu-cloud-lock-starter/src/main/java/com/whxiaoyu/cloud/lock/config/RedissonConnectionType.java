package com.whxiaoyu.cloud.lock.config;

/**
 * 连接方式
 * @author jinxiaoyu
 */
public enum RedissonConnectionType {

    /**
     * 单机部署方式(默认)
     */
    STANDALONE,

    /**
     * 哨兵部署方式
     */
    SENTINEL,

    /**
     * 集群部署方式
     */
    CLUSTER,

    /**
     * 主从部署方式
     */
    MASTERSLAVE

}

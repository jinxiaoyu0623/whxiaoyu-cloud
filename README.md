# whxiaoyu-cloud

#### 介绍
whxiaoyi-cloud 基于spring-cloud-alibaba构建的微服务生态，减少重复造轮子

#### 软件架构
软件架构说明

授权服务 whxiaoyu-auth: 基于security-oauth2-authorization-server构建 

公共组件 whxiaoyu-components 安全认证、数据库持久化、分布式锁、熔断限流等组件

DevOps whxiaoyu-devops 运维服务

实例 whxiaoyi-examples 提供引用实例

网关 whxiaoyu-gateway 基于spring-gateway

消息队列 whxiaoyu-message 使用RocketMQ

事务管理 whxiaoyu-transaction 本地事务和XA事务。(分布式事务开发中...)

用户中心 whxiaoyu-uc 



#### 使用说明

1.  git clone https://gitee.com/whxiaoyu123/whxiaoyu-cloud
2.  mvn clean package
3.  java -jar whxiaoyu-**.jar

#### 各个版本依赖

2.5.x for spring boot 2.5.8 | spring cloud 2020.0.5 | alibaba-cloud 2021.1

2.7.x for spring boot 2.7.8 | spring cloud 2021.0.5 | alibaba-cloud 2021.1






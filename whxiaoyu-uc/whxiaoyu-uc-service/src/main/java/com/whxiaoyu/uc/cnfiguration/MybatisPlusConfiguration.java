package com.whxiaoyu.uc.cnfiguration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 配置
 * @author jinxiaoyu
 * @date 2020/08/14 16:39
 */
@Configuration
@MapperScan(basePackages = "com.whxiaoyu.uc.mapper")
public class MybatisPlusConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

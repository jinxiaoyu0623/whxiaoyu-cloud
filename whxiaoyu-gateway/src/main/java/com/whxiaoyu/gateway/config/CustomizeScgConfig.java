package com.whxiaoyu.gateway.config;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.core.enums.SystemErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * sentinel gateway 配置
 * @author jinxiaoyu
 */
@Slf4j
@Configuration
public class CustomizeScgConfig {

    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return (exchange, throwable) -> {
            log.warn("sentinel blockException info : {} {}",throwable.getClass().getName(),throwable.getMessage());
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ResultDto.error(SystemErrorTypeEnum.SYSTEM_BUSY));
        };
    }
}

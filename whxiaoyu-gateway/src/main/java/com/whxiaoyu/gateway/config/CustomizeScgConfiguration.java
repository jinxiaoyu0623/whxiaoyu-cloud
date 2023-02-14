package com.whxiaoyu.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.component.exception.enums.SystemErrorTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * sentinel异常处理
 * @author jinxiaoyu
 */
@Configuration
public class CustomizeScgConfiguration {

    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return (exchange, throwable) -> ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ResponseResult.error(SystemErrorTypeEnum.SYSTEM_BUSY)));
    }
}

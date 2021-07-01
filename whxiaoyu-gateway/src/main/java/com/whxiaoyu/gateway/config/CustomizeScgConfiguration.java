package com.whxiaoyu.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.exception.enums.SystemErrorTypeEnum;
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
        return (exchange, t) -> ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ResultDto.error(SystemErrorTypeEnum.SYSTEM_BUSY)));
    }
}

package com.whxiaoyu.gateway.controller;

import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.exception.enums.SystemErrorTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author jinxiaoyu
 */
@RestController
public class WebFluxFallbackController {

    @GetMapping("/fallback")
    public Mono<ResultDto<String>> fallback() {
        return Mono.just(ResultDto.error(SystemErrorTypeEnum.SYSTEM_BUSY));
    }
}

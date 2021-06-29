package com.whxiaoyu.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.exception.enums.SystemErrorTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
@Order(-1)
@Component
public class CustomizeErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(getResultDto(ex)));
            } catch (JsonProcessingException e) {
                log.error("object mapper write error : {}",e.getMessage());
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    private ResultDto<String> getResultDto(Throwable throwable) {
        log.error("网关异常: {}",throwable.getMessage());
        if (throwable instanceof NotFoundException) {
            return ResultDto.error(SystemErrorTypeEnum.GATEWAY_NOT_FOUND_SERVICE);
        } else if (throwable instanceof ResponseStatusException) {
            Throwable ex = throwable.getCause();
            if (ex instanceof TimeoutException) {
                return ResultDto.error(SystemErrorTypeEnum.GATEWAY_CONNECT_TIME_OUT);
            }
            return ResultDto.error(SystemErrorTypeEnum.GATEWAY_ERROR.getCode(),"网关异常:" + throwable.getMessage());
        } else {
            return ResultDto.error(SystemErrorTypeEnum.GATEWAY_ERROR);
        }
    }


}

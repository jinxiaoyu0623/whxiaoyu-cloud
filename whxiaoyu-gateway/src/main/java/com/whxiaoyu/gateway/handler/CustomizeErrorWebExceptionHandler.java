package com.whxiaoyu.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.component.exception.enums.SystemErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.ConnectException;


/**
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 * @author jinxiaoyu
 */
@Slf4j
@Order(-1)
@Component
public class CustomizeErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(getResultDto(ex)));
        }));
    }

    private ResponseResult<String> getResultDto(Throwable throwable) {
        log.error("网关异常: {}",throwable.getMessage());
        if (throwable instanceof NotFoundException) {
            return ResponseResult.error(SystemErrorTypeEnum.GATEWAY_NOT_FOUND_SERVICE);
        } else if (throwable instanceof ResponseStatusException) {
            Throwable ex = throwable.getCause();
            if (ex instanceof TimeoutException) {
                return ResponseResult.error(SystemErrorTypeEnum.GATEWAY_CONNECT_TIME_OUT);
            }
            return ResponseResult.error(SystemErrorTypeEnum.GATEWAY_ERROR.getCode(),"网关异常:" + throwable.getMessage());
        } else if (throwable instanceof ConnectException){
            return ResponseResult.error(SystemErrorTypeEnum.SERVICE_CONNECTION_REFUSED);
        } else {
            return ResponseResult.error(SystemErrorTypeEnum.GATEWAY_ERROR);
        }
    }


}

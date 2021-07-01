package com.whxiaoyu.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.exception.enums.SystemErrorTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 自定义限流网关过滤
 * @author jinxiaoyu
 */
@Slf4j
@Component
public class CustomizeRequestRateLimiterGatewayFilterFactory extends RequestRateLimiterGatewayFilterFactory {

    private final RateLimiter defaultRateLimiter;

    private final KeyResolver defaultKeyResolver;

    private final ObjectMapper objectMapper;

    public CustomizeRequestRateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter, KeyResolver defaultKeyResolver,ObjectMapper objectMapper) {
        super(defaultRateLimiter, defaultKeyResolver);
        this.defaultKeyResolver = defaultKeyResolver;
        this.defaultRateLimiter = defaultRateLimiter;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = getOrDefault(config.getKeyResolver(), defaultKeyResolver);
        RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(), defaultRateLimiter);

        return (exchange, chain) -> resolver.resolve(exchange)
                .flatMap(key -> {
                    String routeId = config.getRouteId();
                    if (routeId == null) {
                        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                        routeId = route.getId();
                    }
                    return limiter.isAllowed(routeId, key).flatMap(response -> {

                        for (Map.Entry<String, String> header : response.getHeaders()
                                .entrySet()) {
                            exchange.getResponse().getHeaders().add(header.getKey(),
                                    header.getValue());
                        }

                        if (response.isAllowed()) {
                            return chain.filter(exchange);
                        }

                        //限流返回
                        ServerHttpResponse httpResponse = exchange.getResponse();
                        httpResponse.setStatusCode(config.getStatusCode());
                        httpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer buffer;
                        try {
                            buffer = httpResponse.bufferFactory().wrap(objectMapper.writeValueAsBytes(ResultDto.error(SystemErrorTypeEnum.SYSTEM_BUSY)));
                        } catch (JsonProcessingException e) {
                            log.error("object mapper write error : {}",e.getMessage());
                            buffer = httpResponse.bufferFactory().wrap(new byte[0]);
                        }
                        return httpResponse.writeWith(Mono.just(buffer));
                    });
                });
    }

    private <T> T getOrDefault(T configValue, T defaultValue) {
        return (configValue != null) ? configValue : defaultValue;
    }
}

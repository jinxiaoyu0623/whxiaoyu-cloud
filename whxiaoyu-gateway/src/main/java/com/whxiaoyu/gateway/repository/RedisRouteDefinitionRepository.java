package com.whxiaoyu.gateway.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whxiaoyu.common.core.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 结合actuator api实现集群动态路由配置
 * @author jinxiaoyu
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(CacheConstants.GATEWAY_ROUTES + "*");

        if (keys != null) {
            List<String> list = redisTemplate.opsForValue().multiGet(keys);
            if (list != null && list.size() > 0) {
                list.forEach(s -> {
                    try {
                        routeDefinitions.add(objectMapper.readValue(s,RouteDefinition.class));
                    } catch (JsonProcessingException e) {
                        log.error("object mapper write error : {}",e.getMessage());
                    }
                });
            }
        }
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            if (StringUtils.isEmpty(r.getId())) {
                return Mono.error(new IllegalArgumentException("id may not be empty"));
            }
            try {
                redisTemplate.opsForValue().set(CacheConstants.GATEWAY_ROUTES + r.getId(),objectMapper.writeValueAsString(r));
            } catch (JsonProcessingException e) {
                log.error("object mapper write error : {}",e.getMessage());
            }
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            Boolean flag = redisTemplate.hasKey(CacheConstants.GATEWAY_ROUTES + id);
            if (Boolean.valueOf(true).equals(flag)) {
                redisTemplate.delete(CacheConstants.GATEWAY_ROUTES + id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(
                    new NotFoundException("RouteDefinition not found: " + routeId)));
        });
    }
}

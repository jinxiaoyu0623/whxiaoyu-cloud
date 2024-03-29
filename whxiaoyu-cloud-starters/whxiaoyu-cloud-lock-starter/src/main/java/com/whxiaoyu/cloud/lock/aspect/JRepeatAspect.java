package com.whxiaoyu.cloud.lock.aspect;

import com.whxiaoyu.cloud.commons.core.ResponseResult;
import com.whxiaoyu.cloud.commons.exception.BusinessException;
import com.whxiaoyu.cloud.commons.exception.CustomizeErrorType;
import com.whxiaoyu.cloud.lock.annotation.JRepeat;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.time.Duration;

/**
 * 防止重复提交拦截
 *
 * @author jinxiaoyu
 */
@Aspect
@RequiredArgsConstructor
public class JRepeatAspect {

    private final RedissonClient redissonClient;

    /***
     * 定义controller切入点拦截规则，拦截JRepeat注解的业务方法
     */
    @Pointcut("@annotation(jRepeat)")
    public void pointCut(JRepeat jRepeat) {
    }

    /**
     * AOP分布式锁拦截
     */
    @Around(value = "pointCut(jRepeat)", argNames = "joinPoint,jRepeat")
    public Object repeatSubmit(ProceedingJoinPoint joinPoint, JRepeat jRepeat) throws Throwable {
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        // 获取参数
        Object[] args = joinPoint.getArgs();
        // 进行一些参数的处理
        StringBuilder lockKeyBuilder = new StringBuilder();
        lockKeyBuilder.append(jRepeat.key());
        if (parameterNames != null && parameterNames.length > 0) {
            lockKeyBuilder.append(":");
            for (int i = 0; i < parameterNames.length; i++) {
                if (i == parameterNames.length - 1) {
                    lockKeyBuilder.append(args[i]);
                } else {
                    lockKeyBuilder.append(args[i]).append(":");
                }
            }
        }
        String key = lockKeyBuilder.toString();
        RSet<Integer> rSet = redissonClient.getSet(key);
        if (rSet.isEmpty()) {
            rSet.add(1);
            rSet.expire(Duration.ofMillis(jRepeat.expireTime()));
        } else {
            throw new BusinessException(new CustomizeErrorType(ResponseResult.DEFAULT_FAIL_CODE,jRepeat.message()));
        }
        return joinPoint.proceed();
    }


}

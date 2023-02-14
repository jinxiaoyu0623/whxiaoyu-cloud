package com.whxiaoyu.component.lock.aspect;

import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.component.exception.BusinessException;
import com.whxiaoyu.component.exception.CustomizeErrorType;
import com.whxiaoyu.component.lock.annotation.JLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 分布式锁拦截
 *
 * @author jinxiaoyu
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class JLockAspect {

    private final RedissonClient redissonClient;


    /***
     * 定义controller切入点拦截规则，拦截jLock注解的业务方法
     */
    @Pointcut("@annotation(jLock)")
    public void pointCut(JLock jLock) {
    }

    /**
     * AOP分布式锁拦截
     */
    @Around(value = "pointCut(jLock)", argNames = "joinPoint,jLock")
    public Object repeatSubmit(ProceedingJoinPoint joinPoint, JLock jLock) throws Throwable {
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        Object[] args = joinPoint.getArgs();
        //spel解析器
        ExpressionParser parser = new SpelExpressionParser();
        //spel上下文
        EvaluationContext context = new StandardEvaluationContext();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        // springEl处理
        Expression expression = parser.parseExpression(jLock.key());
        String lockKey = expression.getValue(context,String.class);
        RLock rLock = redissonClient.getLock("lock:" + lockKey);
        // 加锁，lockTime后锁自动释放
        boolean res = rLock.tryLock(0, jLock.expireTime(), jLock.timeUnit());
        try {
            // 处理业务
            if (res) {
                return joinPoint.proceed();
            } else {
                throw new BusinessException(new CustomizeErrorType(ResponseResult.DEFAULT_FAIL_CODE,jLock.message()));
            }
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }

}

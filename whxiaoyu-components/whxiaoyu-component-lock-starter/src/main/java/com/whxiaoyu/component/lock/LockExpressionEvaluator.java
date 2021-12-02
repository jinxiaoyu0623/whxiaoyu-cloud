package com.whxiaoyu.component.lock;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jinxiaoyu
 */
public class LockExpressionEvaluator extends CachedExpressionEvaluator {


    private final Map<ExpressionKey, Expression> keyExpressionMap = new ConcurrentHashMap<>(64);

    /**
     * Create an {@link EvaluationContext}.
     *
     * @param method      the method
     * @param args        the method arguments
     * @param target      the target object
     * @param targetClass the target class
     * @return the evaluation context
     */
    public EvaluationContext createEvaluationContext(Method method, Object[] args, Object target, Class<?> targetClass) {

        LockExpressionRootObject rootObject = new LockExpressionRootObject(method, args, target, targetClass);
        return new LockEvaluationContext(
                rootObject, method, args, getParameterNameDiscoverer());
    }

    @Nullable
    public Object key(String keyExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.keyExpressionMap, methodKey, keyExpression).getValue(evalContext);
    }


}

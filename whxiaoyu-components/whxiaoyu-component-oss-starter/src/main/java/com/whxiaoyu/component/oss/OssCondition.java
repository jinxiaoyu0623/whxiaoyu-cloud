package com.whxiaoyu.component.oss;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

/**
 * 与所有存储服务配置类一起使用的通用条件
 *
 * @author jinxiaoyu
 */
public class OssCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String sourceClass = "";
        if (metadata instanceof ClassMetadata) {
            sourceClass = ((ClassMetadata) metadata).getClassName();
        }
        ConditionMessage.Builder message = ConditionMessage.forCondition("Oss", sourceClass);
        Environment environment = context.getEnvironment();
        try {
            BindResult<OssType> specified = Binder.get(environment).bind("oss.type", OssType.class);
            if (!specified.isBound()) {
                return ConditionOutcome.match(message.because("automatic oss type"));
            }
            OssType required = OssConfigurations.getType(((AnnotationMetadata) metadata).getClassName());
            if (specified.get() == required) {
                return ConditionOutcome.match(message.because(specified.get() + " oss type"));
            }
        }
        catch (BindException ex) {
        }
        return ConditionOutcome.noMatch(message.because("unknown oss type"));
    }
}

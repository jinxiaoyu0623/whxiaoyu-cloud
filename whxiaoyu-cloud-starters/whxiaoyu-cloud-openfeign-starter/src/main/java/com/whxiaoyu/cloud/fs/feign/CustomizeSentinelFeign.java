package com.whxiaoyu.cloud.fs.feign;

import com.alibaba.cloud.sentinel.feign.SentinelContractHolder;
import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 自定义sentinel断路器
 * @author jinxiaoyu
 */
public class CustomizeSentinelFeign {

    public CustomizeSentinelFeign() {

    }

    public static CustomizeSentinelFeign.Builder builder() {
        return new CustomizeSentinelFeign.Builder();
    }

    public static final class Builder extends Feign.Builder implements ApplicationContextAware {

        private Contract contract = new Contract.Default();

        private ApplicationContext applicationContext;

        private FeignContext feignContext;

        @Override
        public Feign.Builder invocationHandlerFactory(
                InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }

        @Override
        public CustomizeSentinelFeign.Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                @Override
                public InvocationHandler create(Target target,
                                                Map<Method, MethodHandler> dispatch) {
                    GenericApplicationContext gctx = (GenericApplicationContext) CustomizeSentinelFeign.Builder.this.applicationContext;
                    BeanDefinition def = gctx.getBeanDefinition(target.type().getName());

                    /*
                     * Due to the change of the initialization sequence,
                     * BeanFactory.getBean will cause a circular dependency. So
                     * FeignClientFactoryBean can only be obtained from BeanDefinition
                     */
                    FeignClientFactoryBean feignClientFactoryBean = (FeignClientFactoryBean) def
                            .getAttribute("feignClientsRegistrarFactoryBean");

                    Class fallback = feignClientFactoryBean.getFallback();
                    Class fallbackFactory = feignClientFactoryBean.getFallbackFactory();
                    String beanName = feignClientFactoryBean.getContextId();
                    if (!StringUtils.hasText(beanName)) {
                        beanName = (String) getFieldValue(feignClientFactoryBean, "name");
                    }

                    Object fallbackInstance;
                    FallbackFactory fallbackFactoryInstance;
                    // check fallback and fallbackFactory properties
                    if (void.class != fallback) {
                        fallbackInstance = getFromContext(beanName, "fallback", fallback,
                                target.type());
                        return new CustomizeSentinelInvocationHandler(target, dispatch,
                                new FallbackFactory.Default(fallbackInstance));
                    }
                    if (void.class != fallbackFactory) {
                        fallbackFactoryInstance = (FallbackFactory) getFromContext(
                                beanName, "fallbackFactory", fallbackFactory,
                                FallbackFactory.class);
                        return new CustomizeSentinelInvocationHandler(target, dispatch,
                                fallbackFactoryInstance);
                    }

                    //设置全局异常回退
                    CustomizeFallbackFactory customizeFallbackFactory = new CustomizeFallbackFactory(target);
                    return new CustomizeSentinelInvocationHandler(target, dispatch,customizeFallbackFactory);
                }

                private Object getFromContext(String name, String type,
                                              Class fallbackType, Class targetType) {
                    Object fallbackInstance = feignContext.getInstance(name,
                            fallbackType);
                    if (fallbackInstance == null) {
                        throw new IllegalStateException(String.format(
                                "No %s instance of type %s found for feign client %s",
                                type, fallbackType, name));
                    }
                    // when fallback is a FactoryBean, should determine the type of instance
                    if (fallbackInstance instanceof FactoryBean<?>) {
                        try {
                            fallbackInstance = ((FactoryBean<Object>) fallbackInstance).getObject();
                        }
                        catch (Exception e) {
                            throw new IllegalStateException(type + " create fail", e);
                        }
                        fallbackType = fallbackInstance.getClass();
                    }

                    if (!targetType.isAssignableFrom(fallbackType)) {
                        throw new IllegalStateException(String.format(
                                "Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s",
                                type, fallbackType, targetType, name));
                    }
                    return fallbackInstance;
                }
            });

            super.contract(new SentinelContractHolder(contract));
            return super.build();
        }

        private Object getFieldValue(Object instance, String fieldName) {
            Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    return field.get(instance);
                }
                catch (IllegalAccessException e) {
                    // ignore
                }
            }
            return null;
        }



        @Override
        public void setApplicationContext(ApplicationContext applicationContext)
                throws BeansException {
            this.applicationContext = applicationContext;
            this.feignContext = applicationContext.getBean(FeignContext.class);
        }

    }
}
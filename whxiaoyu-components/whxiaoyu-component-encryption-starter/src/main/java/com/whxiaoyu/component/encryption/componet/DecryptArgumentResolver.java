package com.whxiaoyu.component.encryption.componet;

import com.whxiaoyu.component.encryption.annotation.Decrypt;
import com.whxiaoyu.component.encryption.provider.RsaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 接口url参数解密
 * @author jinxiaoyu
 */
public class DecryptArgumentResolver implements HandlerMethodArgumentResolver {

    private RsaProvider rsaProvider;

    @Autowired
    public void setRsaProvider(RsaProvider rsaProvider) {
        this.rsaProvider = rsaProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Decrypt.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        String paramName = methodParameter.getParameterName();
        String[] paramValues = nativeWebRequest.getParameterValues(paramName);
        if (paramValues != null && paramValues.length > 0) {
            String encryptedData = paramValues[0];
            return rsaProvider.decryptedData(encryptedData);
        }
        return null;
    }
}

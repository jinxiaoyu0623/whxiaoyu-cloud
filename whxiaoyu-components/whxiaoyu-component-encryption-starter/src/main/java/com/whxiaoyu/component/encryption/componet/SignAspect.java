package com.whxiaoyu.component.encryption.componet;

import com.whxiaoyu.component.encryption.annotation.Sign;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jinxiaoyu
 */
@Aspect
public class SignAspect {

    @Pointcut("@annotation(com.whxiaoyu.component.encryption.annotation.Sign)")
    public void sign() {
    }


    @Before("sign()")
    public void sign(JoinPoint joinPoint) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes not null");
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Assert.notNull(request, "request not null");
        //获取header信息
        String sign = request.getHeader("x-sign");
        String timestamp = request.getHeader("x-timestamp");
        String nonce = request.getHeader("x-nonce");

        //获取请求中的所以参数
        Map<String, String[]> paramMap = request.getParameterMap();

        //将参数转换Mao
        Map<String, String> params = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        Sign annotation = method.getAnnotation(Sign.class);

        params.put("timestamp",timestamp);
        params.put("nonce",nonce);
        params.put("secret",annotation.key());

        // 对参数进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);

        //参数组合字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }

        String checkSign = DigestUtils.sha256Hex(stringBuilder.toString());

        boolean verify = checkSign.equals(sign);

        if (!verify) {
            throw new RuntimeException("非法的签名参数");
        }

    }
}

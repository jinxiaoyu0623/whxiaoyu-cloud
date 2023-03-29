package com.whxiaoyu.component.encryption.componet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.component.encryption.annotation.Encrypt;
import com.whxiaoyu.component.encryption.provider.RsaProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 接口数据返回加密
 * @author jinxiaoyu
 */
@RestControllerAdvice
public class EncryptResponseBody implements ResponseBodyAdvice<ResponseResult> {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private RsaProvider rsaProvider;

    @Autowired
    public void setRsaProvider(RsaProvider rsaProvider) {
        this.rsaProvider = rsaProvider;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public ResponseResult beforeBodyWrite(ResponseResult body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> httpMessageConverterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Object data = body.getData();
        if (data == null) {
            return ResponseResult.error(500, "数据为空");
        }

        //如果是字符串
        if (body.getData() instanceof String) {
            return ResponseResult.ok(rsaProvider.encryptedData((String) data));
        }
        String jsonStr = gson.toJson(body.getData());
        return ResponseResult.ok(rsaProvider.encryptedData(jsonStr));
    }

}

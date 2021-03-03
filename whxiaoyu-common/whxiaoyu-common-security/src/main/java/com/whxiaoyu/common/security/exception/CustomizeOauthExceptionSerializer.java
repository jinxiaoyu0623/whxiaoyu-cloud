package com.whxiaoyu.common.security.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 异常返回序列化
 * @author jinxiaoyu
 */
public class CustomizeOauthExceptionSerializer extends StdSerializer<CustomizeOauthException> {

    private static final long serialVersionUID = 5332001460687857316L;

    public CustomizeOauthExceptionSerializer() {
        super(CustomizeOauthException.class);
    }

    @Override
    public void serialize(CustomizeOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.getResult());
    }
}

package com.whxiaoyu.cloud.auth.provider;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whxiaoyu.cloud.commons.core.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author jinxiaoyu
 */
public class CustomizeUserDetailsDeserializer extends JsonDeserializer<CustomizeUserDetails> {
    @Override
    public CustomizeUserDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode jsonNode = mapper.readTree(jsonParser);
        User user = mapper.convertValue(jsonNode.findValue("user"), User.class);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<JsonNode> authoritiesNode = jsonNode.findValues("authorities");
        if (CollectionUtil.isNotEmpty(authoritiesNode)) {
            for (JsonNode authority : authoritiesNode) {
                authorities.add(new SimpleGrantedAuthority(authority.get(1).findValue("authority").asText()));
            }
        }
        return new CustomizeUserDetails(user, authorities);


    }
}

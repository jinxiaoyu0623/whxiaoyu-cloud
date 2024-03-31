package com.whxiaoyu.cloud.auth.provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author jinxiaoyu
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS
)
@JsonDeserialize(
        using = CustomizeUserDetailsDeserializer.class
)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class CustomizeUserDetailsMixIn {
}

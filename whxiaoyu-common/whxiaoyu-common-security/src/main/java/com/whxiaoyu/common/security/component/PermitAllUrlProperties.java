package com.whxiaoyu.common.security.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * 对外直接暴露URL
 * @author jinxiaoyu
 */
@ConfigurationProperties(prefix = "security.ignore")
public class PermitAllUrlProperties {

    private Set<String> urls;

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}

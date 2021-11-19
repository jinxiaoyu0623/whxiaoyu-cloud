package com.whxiaoyu.component.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Set;

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

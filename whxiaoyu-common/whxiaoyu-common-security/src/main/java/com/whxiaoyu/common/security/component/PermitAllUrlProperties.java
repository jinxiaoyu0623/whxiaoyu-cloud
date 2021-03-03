package com.whxiaoyu.common.security.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * 对外直接暴露URL
 * @author jinxiaoyu
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "security.ignore")
public class PermitAllUrlProperties {

    /**
     * 默认放行的url
     */
    private static final Set<String> DEFAULT_IGNORE_URLS = new HashSet<String>() {
        private static final long serialVersionUID = -6197785836525666833L;
        { add("css/**"); add("/js/**");add("/images/**");add("/plugins/**");add("/favicon.ico");add("/api/**");}
    };

    private Set<String> urls;

    public Set<String> getUrls() {
        return urls == null ? DEFAULT_IGNORE_URLS : urls;
    }

    public void setUrls(Set<String> urls) {
        if (urls != null) {
            urls.addAll(DEFAULT_IGNORE_URLS);
        }
        this.urls = urls;
    }
}

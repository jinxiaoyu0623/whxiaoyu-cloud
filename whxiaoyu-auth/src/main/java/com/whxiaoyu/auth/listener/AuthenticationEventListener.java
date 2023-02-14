package com.whxiaoyu.auth.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 监听security认证事件
 * @author jinxiaoyu
 */
@Slf4j
@Component
public class AuthenticationEventListener {

    /**
     * 表单登录成功事件
     */
    @Async("customizeTaskExecutor")
    @EventListener(InteractiveAuthenticationSuccessEvent.class)
    public void formLoginSuccessEvent(InteractiveAuthenticationSuccessEvent event) {
        log.info( "login success : {}",event.getAuthentication().getName());
    }

    /**
     * 登录失败事件
     */
    @Async("customizeTaskExecutor")
    @EventListener(AuthenticationFailureBadCredentialsEvent.class)
    public void loginFailEvent(AuthenticationFailureBadCredentialsEvent event) {
        log.info( "login fail : {} msg: {}",event.getAuthentication().getName(),event.getException().getMessage());
    }

    /**
     * 退出登录事件
     */
    @Async("customizeTaskExecutor")
    @EventListener(LogoutSuccessEvent.class)
    public void logoutSuccessEvent(LogoutSuccessEvent event) {
        log.info( "logout success : {}",event.getAuthentication().getName());
    }



}

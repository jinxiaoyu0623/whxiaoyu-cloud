package com.whxiaoyu.uc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  客户端信息
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Long accessTokenValidity;

    private Long refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;


}

package com.whxiaoyu.common.core.constant;

/**
 * 安全组件常量类
 * @author jinxiaoyu
 */
public final class SecurityConstants {

	/**
	 * 角色前缀
	 */
	public static final String ROLE = "ROLE_";

	/**
	 * 默认登录URL
	 */
	public static final String OAUTH_TOKEN_URL = "/oauth/token";

	/**
	 * grant_type
	 */
	public static final String REFRESH_TOKEN = "refresh_token";

	/**
	 * {bcrypt} 加密的特征码
	 */
	public static final String BCRYPT = "{bcrypt}";

	/***
	 * 资源服务器默认bean名称
	 */
	public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

	/**
	 * 验证码有效期,默认 60秒
	 */
	public static final long CODE_TIME = 60;

}

package com.whxiaoyu.component.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author jinxiaoyu
 */
@Getter
@Setter
public class User extends Dto {

    private static final long serialVersionUID = 3207159221340062422L;


    private String userId;

    /**
     * 用户名
     */
    private String username;

    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户标记 0-正常，9-锁定
     */
    @JsonIgnore
    private String userStatus;

    /**
     * 扩展信息
     */
    private Map<String,Object> expandInfo = Collections.emptyMap();


}

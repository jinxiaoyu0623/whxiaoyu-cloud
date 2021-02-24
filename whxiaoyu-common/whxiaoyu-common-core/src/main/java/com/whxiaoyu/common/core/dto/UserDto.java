package com.whxiaoyu.common.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jinxiaoyu
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 3207159221340062422L;

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
     * 用户状态 （锁定，临时不可登录）
     */
    private String userStatus;

}

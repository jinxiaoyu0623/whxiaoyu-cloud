package com.whxiaoyu.component.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
     * 机构ID
     */
    private Integer orgId;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * 用户标记 0-正常，9-锁定
     */
    private String userStatus;

    /**
     * 拥有的角色
     */
    private List<Role> roleList;

    /**
     * 机构名称
     */
    private String orgName;


    /**
     * 本机构下所在子机构 包含本身机构
     */
    private List<Integer> orgIds;


}

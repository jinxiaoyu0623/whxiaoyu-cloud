package com.whxiaoyu.uc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author jinxiaoyu
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
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
     * 0-正常，1-删除
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}

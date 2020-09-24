package com.whxiaoyu.uc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private Integer roleId;


}

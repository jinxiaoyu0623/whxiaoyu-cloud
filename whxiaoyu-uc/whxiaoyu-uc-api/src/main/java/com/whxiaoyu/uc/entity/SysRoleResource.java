package com.whxiaoyu.uc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 资源ID
     */
    private Integer resourceId;


}

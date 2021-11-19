package com.whxiaoyu.component.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色信息
 *
 * @author jinxiaoyu
 */
@Getter
@Setter
public class Role extends Dto {

    private static final long serialVersionUID = 5000643358657497908L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标示
     */
    private String roleCode;
}

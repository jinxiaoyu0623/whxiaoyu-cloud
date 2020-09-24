package com.whxiaoyu.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Integer resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 前端URL
     */
    private String path;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 0-开启，1- 关闭
     */
    private String keepAlive;

    /**
     * 资源类型 （0菜单 1按钮）
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    private String delFlag;


}

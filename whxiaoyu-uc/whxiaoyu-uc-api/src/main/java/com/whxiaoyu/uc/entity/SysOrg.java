package com.whxiaoyu.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "org_id", type = IdType.AUTO)
    private Integer orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 父节点
     */
    private Integer parentId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delFlag;


}

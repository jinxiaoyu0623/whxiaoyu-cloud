package com.whxiaoyu.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 机构单位
 * </p>
 *
 * @author jinxiaoyu
 * @since 2021-07-01
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

    /**
     * 负责人
     */
    private String person;

    /**
     * 联系电话
     */
    private String phome;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delFlag;


}

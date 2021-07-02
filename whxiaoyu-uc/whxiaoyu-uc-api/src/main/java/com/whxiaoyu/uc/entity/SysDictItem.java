package com.whxiaoyu.uc.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 数据子字典
 * </p>
 *
 * @author jinxiaoyu
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 父字典ID
     */
    private Integer dictId;

    /**
     * 字典名称
     */
    private String itemName;

    /**
     * 字典代码
     */
    private String itemCode;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 逻辑删除 1 删除
     */
    private String delFlag;


}

package com.whxiaoyu.uc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 数据主字典
 * </p>
 *
 * @author jinxiaoyu
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dict_id", type = IdType.AUTO)
    private Integer dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典码
     */
    private String dictCode;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 逻辑删除
     */
    private String delFlag;


}

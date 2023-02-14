package com.whxiaoyu.transaction;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxiaoyu.transaction.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户关联角色 Mapper 接口
 * </p>
 *
 * @author jinxiaoyu
 * @since 2021-07-01
 */
@Mapper
@DS("slave")
public interface UserRoleMapper extends BaseMapper<SysUserRole> {

}

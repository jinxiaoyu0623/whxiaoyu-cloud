package com.whxiaoyu.cloud.auth.provider;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jinxiaoyu
 */
@Mapper
public interface RoleMapper {

    /**
     * 获取用户角色
     * @param userId 用户ID
     * @return List
     */
    @Select({"SELECT r.role_code FROM sys_user_role as ur " +
            "JOIN sys_role as r ON r.role_id = ur.role_id " +
            "WHERE user_id = #{userId}"})
    List<String> selectByUserId(String userId);
}

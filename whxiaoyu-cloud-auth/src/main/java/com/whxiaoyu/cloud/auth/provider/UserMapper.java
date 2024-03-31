package com.whxiaoyu.cloud.auth.provider;

import com.whxiaoyu.cloud.commons.core.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author jinxiaoyu
 */
@Mapper
public interface UserMapper {

    /**
     * 根据账户获取用户信息
     * @param username username
     * @return userInfo
     */
    @Select({"select * from sys_user where username = #{username} and del_flag = '0' limit 1"})
    User selectByUsername(String username);
}

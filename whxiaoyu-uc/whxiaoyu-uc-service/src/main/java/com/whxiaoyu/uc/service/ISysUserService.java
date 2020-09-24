package com.whxiaoyu.uc.service;

import com.whxiaoyu.common.core.dto.UserDto;
import com.whxiaoyu.uc.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 获取用户信息
     * @param username username
     * @return userInfo
     */
    UserDto getUserDtoByUsername(String username);
}

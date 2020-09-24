package com.whxiaoyu.uc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whxiaoyu.common.core.dto.UserDto;
import com.whxiaoyu.common.core.enums.AuthErrorType;
import com.whxiaoyu.common.core.enums.SystemErrorType;
import com.whxiaoyu.common.core.exception.CustomException;
import com.whxiaoyu.uc.entity.SysUser;
import com.whxiaoyu.uc.mapper.SysUserMapper;
import com.whxiaoyu.uc.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public UserDto getUserDtoByUsername(String username) {
        SysUser sysUser = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername,username));
        if (sysUser == null) {
            throw new CustomException(AuthErrorType.USERNAME_NOT_FOUND);
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(sysUser,userDto);
        return userDto;
    }
}

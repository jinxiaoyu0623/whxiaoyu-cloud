package com.whxiaoyu.transaction;

import com.whxiaoyu.transaction.entity.SysUser;
import com.whxiaoyu.transaction.entity.SysUserRole;
import com.whxiaoyu.transaction.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final SysUserMapper sysUserMapper;
    private final UserRoleMapper userRoleMapper;


    @Transactional(rollbackFor = Exception.class)
    public boolean save() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("111112");
        sysUser.setUsername("111131");
        sysUser.setPassword("1111111");
        sysUser.setPhone("11111111");
        sysUser.setNickName("111111");
        sysUserMapper.insert(sysUser);

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getUserId());
        sysUserRole.setRoleId(1);
        userRoleMapper.insert(sysUserRole);

        String str = null;
        str.equals(11);

        return false;
    }


}

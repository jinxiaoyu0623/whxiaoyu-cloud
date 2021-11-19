package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.component.dto.User;
import com.whxiaoyu.component.exception.enums.SystemErrorTypeEnum;
import com.whxiaoyu.uc.entity.SysUser;
import com.whxiaoyu.uc.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户控制器
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class SysUserController {

    private final ISysUserService sysUserService;

    /**
     * 系统用户数据
     */
    @GetMapping("/list")
    public ResponseResult<List<SysUser>> list() {
        return ResponseResult.ok(sysUserService.list());
    }

    /**
     * 系统用户分页数据
     */
    @GetMapping("/pageData")
    public ResponseResult<IPage<SysUser>> pageData() {
        return ResponseResult.ok(sysUserService.page(new Page<>()));
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info")
    public ResponseResult<User> info(Authentication authentication) {
        String username = authentication.getName();
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return ResponseResult.error(SystemErrorTypeEnum.DATA_NOT_FOUND);
        }
        User userDto = new User();
        BeanUtils.copyProperties(sysUser, userDto);
        return ResponseResult.ok(userDto);
    }

    /**
     * 详情系统用户信息
     */
    @GetMapping("/detail")
    public ResponseResult<SysUser> detail(@RequestParam String userId) {
        SysUser sysUser = sysUserService.getById(userId);
        return ResponseResult.ok(sysUser);
    }

    /**
     * 保存系统用户信息
     */
    @PostMapping("/save")
    public ResponseResult<Boolean> save(SysUser sysUser) {
        return ResponseResult.ok(sysUserService.save(sysUser));
    }

    /**
     * 修改系统用户信息
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(SysUser sysUser) {
        return ResponseResult.ok(sysUserService.updateById(sysUser));
    }

    /**
     * 删除系统用户记录
     */
    @PostMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestParam String userId) {
        return ResponseResult.ok(sysUserService.removeById(userId));
    }

}


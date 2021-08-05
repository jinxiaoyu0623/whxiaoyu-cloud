package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.core.dto.UserDto;
import com.whxiaoyu.common.security.CustomizeUserDetails;
import com.whxiaoyu.uc.entity.SysUser;
import com.whxiaoyu.uc.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户控制器
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
    public ResultDto<List<SysUser>> list() {
        return ResultDto.ok(sysUserService.list());
    }

    /**
     * 系统用户分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<SysUser>> pageData() {
        return ResultDto.ok(sysUserService.page(new Page<>()));
    }

    /**
     * 获取当前用户信息
     */
    @PreAuthorize("hasAuthority('#oauth2')")
    @GetMapping("/info")
    public ResultDto<UserDto> info(Authentication authentication) {
        CustomizeUserDetails userDetails = (CustomizeUserDetails) authentication.getPrincipal();
        return ResultDto.ok(userDetails.getUserDto());
    }

    /**
     * 详情系统用户信息
     */
    @GetMapping("/detail")
    public ResultDto<SysUser> detail(@RequestParam String userId) {
        SysUser sysUser = sysUserService.getById(userId);
        return ResultDto.ok(sysUser);
    }

    /**
     * 保存系统用户信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(SysUser sysUser) {
        return ResultDto.ok(sysUserService.save(sysUser));
    }

    /**
     * 修改系统用户信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(SysUser sysUser) {
        return ResultDto.ok(sysUserService.updateById(sysUser));
    }

    /**
     * 删除系统用户记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam String userId) {
        return ResultDto.ok(sysUserService.removeById(userId));
    }

}


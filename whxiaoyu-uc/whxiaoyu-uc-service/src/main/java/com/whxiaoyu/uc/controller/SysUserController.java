package com.whxiaoyu.uc.controller;


import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.core.dto.UserDto;
import com.whxiaoyu.uc.entity.SysUser;
import com.whxiaoyu.uc.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author jinxiaoyu
 * @since 2020-08-14
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private final ISysUserService sysUserService;


    @GetMapping("/list")
    public ResultDto<List<SysUser>> list() {
        return ResultDto.ok(sysUserService.list());
    }

    @GetMapping("/userInfo")
    public ResultDto<UserDto> userInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResultDto.ok(sysUserService.getUserDtoByUsername(username));
    }

}


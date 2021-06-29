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
 * 系统用户
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class SysUserController {

    private final ISysUserService sysUserService;


    @GetMapping("/list")
    public ResultDto<List<SysUser>> list() {
        return ResultDto.ok(sysUserService.list());
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public ResultDto<UserDto> userInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResultDto.ok(sysUserService.getUserDtoByUsername(username));
    }

}


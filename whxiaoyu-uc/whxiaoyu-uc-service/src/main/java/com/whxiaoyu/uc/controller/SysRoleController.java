package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysRole;
import com.whxiaoyu.uc.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色控制器
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class SysRoleController {

    private final ISysRoleService sysRoleService;

    /**
     * 系统角色数据
     */
    @GetMapping("/list")
    public ResultDto<List<SysRole>> list() {
        return ResultDto.ok(sysRoleService.list());
    }

    /**
     * 系统角色分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<SysRole>> pageData() {
        return ResultDto.ok(sysRoleService.page(new Page<>()));
    }

    /**
     * 详情系统角色信息
     */
    @GetMapping("/detail")
    public ResultDto<SysRole> detail(@RequestParam Integer roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        return ResultDto.ok(sysRole);
    }

    /**
     * 保存系统角色信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(SysRole sysRole) {
        return ResultDto.ok(sysRoleService.save(sysRole));
    }

    /**
     * 修改系统角色信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(SysRole sysRole) {
        return ResultDto.ok(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除系统角色记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam Integer roleId) {
        return ResultDto.ok(sysRoleService.removeById(roleId));
    }

}


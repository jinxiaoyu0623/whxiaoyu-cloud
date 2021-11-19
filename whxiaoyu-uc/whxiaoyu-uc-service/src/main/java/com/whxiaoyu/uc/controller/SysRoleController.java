package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.uc.entity.SysRole;
import com.whxiaoyu.uc.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统角色控制器
 *
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
    public ResponseResult<List<SysRole>> list() {
        return ResponseResult.ok(sysRoleService.list());
    }

    /**
     * 系统角色分页数据
     */
    @GetMapping("/pageData")
    public ResponseResult<IPage<SysRole>> pageData() {
        return ResponseResult.ok(sysRoleService.page(new Page<>()));
    }

    /**
     * 详情系统角色信息
     */
    @GetMapping("/detail")
    public ResponseResult<SysRole> detail(@RequestParam Integer roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        return ResponseResult.ok(sysRole);
    }

    /**
     * 保存系统角色信息
     */
    @PostMapping("/save")
    public ResponseResult<Boolean> save(SysRole sysRole) {
        return ResponseResult.ok(sysRoleService.save(sysRole));
    }

    /**
     * 修改系统角色信息
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(SysRole sysRole) {
        return ResponseResult.ok(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除系统角色记录
     */
    @PostMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestParam Integer roleId) {
        return ResponseResult.ok(sysRoleService.removeById(roleId));
    }

}


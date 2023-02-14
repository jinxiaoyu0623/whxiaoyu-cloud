package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.component.core.ResponseResult;
import com.whxiaoyu.uc.entity.SysResource;
import com.whxiaoyu.uc.service.ISysResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统资源控制器
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/resource")
public class SysResourceController {

    private final ISysResourceService sysResourceService;

    /**
     * 系统资源数据
     */
    @GetMapping("/list")
    public ResponseResult<List<SysResource>> list() {
        return ResponseResult.ok(sysResourceService.list());
    }

    /**
     * 系统资源分页数据
     */
    @GetMapping("/pageData")
    public ResponseResult<IPage<SysResource>> pageData() {
        return ResponseResult.ok(sysResourceService.page(new Page<>()));
    }

    /**
     * 详情系统资源信息
     */
    @GetMapping("/detail")
    public ResponseResult<SysResource> detail(@RequestParam Integer resourceId) {
        SysResource sysResource = sysResourceService.getById(resourceId);
        return ResponseResult.ok(sysResource);
    }

    /**
     * 保存系统资源信息
     */
    @PostMapping("/save")
    public ResponseResult<Boolean> save(SysResource sysResource) {
        return ResponseResult.ok(sysResourceService.save(sysResource));
    }

    /**
     * 修改系统资源信息
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(SysResource sysResource) {
        return ResponseResult.ok(sysResourceService.updateById(sysResource));
    }

    /**
     * 删除系统资源记录
     */
    @PostMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestParam Integer resourceId) {
        return ResponseResult.ok(sysResourceService.removeById(resourceId));
    }

}


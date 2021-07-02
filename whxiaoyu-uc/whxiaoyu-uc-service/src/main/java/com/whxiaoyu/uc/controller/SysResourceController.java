package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysResource;
import com.whxiaoyu.uc.service.ISysResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统资源控制器
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
    public ResultDto<List<SysResource>> list() {
        return ResultDto.ok(sysResourceService.list());
    }

    /**
     * 系统资源分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<SysResource>> pageData() {
        return ResultDto.ok(sysResourceService.page(new Page<>()));
    }

    /**
     * 详情系统资源信息
     */
    @GetMapping("/detail")
    public ResultDto<SysResource> detail(@RequestParam Integer resourceId) {
        SysResource sysResource = sysResourceService.getById(resourceId);
        return ResultDto.ok(sysResource);
    }

    /**
     * 保存系统资源信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(SysResource sysResource) {
        return ResultDto.ok(sysResourceService.save(sysResource));
    }

    /**
     * 修改系统资源信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(SysResource sysResource) {
        return ResultDto.ok(sysResourceService.updateById(sysResource));
    }

    /**
     * 删除系统资源记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam Integer resourceId) {
        return ResultDto.ok(sysResourceService.removeById(resourceId));
    }

}


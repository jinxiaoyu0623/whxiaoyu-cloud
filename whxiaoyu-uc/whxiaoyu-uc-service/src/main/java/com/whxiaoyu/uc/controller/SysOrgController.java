package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysOrg;
import com.whxiaoyu.uc.service.ISysOrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构单位控制器
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/org")
public class SysOrgController {

    private final ISysOrgService sysOrgService;

    /**
     * 机构单位数据
     */
    @GetMapping("/list")
    public ResultDto<List<SysOrg>> list() {
        return ResultDto.ok(sysOrgService.list());
    }

    /**
     * 机构单位分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<SysOrg>> pageData() {
        return ResultDto.ok(sysOrgService.page(new Page<>()));
    }

    /**
     * 详情机构单位信息
     */
    @GetMapping("/detail")
    public ResultDto<SysOrg> detail(@RequestParam Integer orgId) {
        SysOrg sysOrg = sysOrgService.getById(orgId);
        return ResultDto.ok(sysOrg);
    }

    /**
     * 保存机构单位信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(SysOrg sysOrg) {
        return ResultDto.ok(sysOrgService.save(sysOrg));
    }

    /**
     * 修改机构单位信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(SysOrg sysOrg) {
        return ResultDto.ok(sysOrgService.updateById(sysOrg));
    }

    /**
     * 删除机构单位记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam Integer orgId) {
        return ResultDto.ok(sysOrgService.removeById(orgId));
    }

}


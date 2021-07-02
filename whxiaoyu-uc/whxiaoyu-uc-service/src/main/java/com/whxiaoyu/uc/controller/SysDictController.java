package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysDict;
import com.whxiaoyu.uc.service.ISysDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据主字典控制器
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dict")
public class SysDictController {

    private final ISysDictService sysDictService;

    /**
     * 数据主字典数据
     */
    @GetMapping("/list")
    public ResultDto<List<SysDict>> list() {
        return ResultDto.ok(sysDictService.list());
    }

    /**
     * 数据主字典分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<SysDict>> pageData() {
        return ResultDto.ok(sysDictService.page(new Page<>()));
    }

    /**
     * 详情数据主字典信息
     */
    @GetMapping("/detail")
    public ResultDto<SysDict> detail(@RequestParam Integer dictId) {
        SysDict sysDict = sysDictService.getById(dictId);
        return ResultDto.ok(sysDict);
    }

    /**
     * 保存数据主字典信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(SysDict sysDict) {
        return ResultDto.ok(sysDictService.save(sysDict));
    }

    /**
     * 修改数据主字典信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(SysDict sysDict) {
        return ResultDto.ok(sysDictService.updateById(sysDict));
    }

    /**
     * 删除数据主字典记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam Integer dictId) {
        return ResultDto.ok(sysDictService.removeById(dictId));
    }

}


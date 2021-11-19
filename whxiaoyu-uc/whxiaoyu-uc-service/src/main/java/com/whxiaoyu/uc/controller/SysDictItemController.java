package com.whxiaoyu.uc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.component.dto.ResponseResult;
import com.whxiaoyu.uc.entity.SysDictItem;
import com.whxiaoyu.uc.service.ISysDictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据子字典控制器
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dictItem")
public class SysDictItemController {

    private final ISysDictItemService sysDictItemService;

    /**
     * 数据子字典数据
     */
    @GetMapping("/list")
    public ResponseResult<List<SysDictItem>> list() {
        return ResponseResult.ok(sysDictItemService.list());
    }

    /**
     * 数据子字典分页数据
     */
    @GetMapping("/pageData")
    public ResponseResult<IPage<SysDictItem>> pageData() {
        return ResponseResult.ok(sysDictItemService.page(new Page<>()));
    }

    /**
     * 详情数据子字典信息
     */
    @GetMapping("/detail")
    public ResponseResult<SysDictItem> detail(@RequestParam Integer id) {
        SysDictItem sysDictItem = sysDictItemService.getById(id);
        return ResponseResult.ok(sysDictItem);
    }

    /**
     * 保存数据子字典信息
     */
    @PostMapping("/save")
    public ResponseResult<Boolean> save(SysDictItem sysDictItem) {
        return ResponseResult.ok(sysDictItemService.save(sysDictItem));
    }

    /**
     * 修改数据子字典信息
     */
    @PostMapping("/update")
    public ResponseResult<Boolean> update(SysDictItem sysDictItem) {
        return ResponseResult.ok(sysDictItemService.updateById(sysDictItem));
    }

    /**
     * 删除数据子字典记录
     */
    @PostMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestParam Integer id) {
        return ResponseResult.ok(sysDictItemService.removeById(id));
    }

}


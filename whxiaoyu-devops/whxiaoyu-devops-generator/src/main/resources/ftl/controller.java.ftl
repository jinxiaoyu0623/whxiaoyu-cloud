package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whxiaoyu.common.core.dto.ResultDto;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${table.comment}控制器
 * @author ${author}
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName} {

    private final ${table.serviceName} ${entity?uncap_first}Service;

    /**
     * ${table.comment}数据
     */
    @GetMapping("/list")
    public ResultDto<List<${entity}>> list() {
        return ResultDto.ok(${entity?uncap_first}Service.list());
    }

    /**
     * ${table.comment}分页数据
     */
    @GetMapping("/pageData")
    public ResultDto<IPage<${entity}>> pageData() {
        return ResultDto.ok(${entity?uncap_first}Service.page(new Page<>()));
    }

    /**
     * 详情${table.comment}信息
     */
    @GetMapping("/detail")
    public ResultDto<${entity}> detail(@RequestParam ${table.fields[0].propertyType} ${table.fields[0].propertyName}) {
        ${entity} ${entity?uncap_first} = ${entity?uncap_first}Service.getById(${table.fields[0].propertyName});
        return ResultDto.ok(${entity?uncap_first});
    }

    /**
     * 保存${table.comment}信息
     */
    @PostMapping("/save")
    public ResultDto<Boolean> save(${entity} ${entity?uncap_first}) {
        return ResultDto.ok(${entity?uncap_first}Service.save(${entity?uncap_first}));
    }

    /**
     * 修改${table.comment}信息
     */
    @PostMapping("/update")
    public ResultDto<Boolean> update(${entity} ${entity?uncap_first}) {
        return ResultDto.ok(${entity?uncap_first}Service.updateById(${entity?uncap_first}));
    }

    /**
     * 删除${table.comment}记录
     */
    @PostMapping("/delete")
    public ResultDto<Boolean> delete(@RequestParam ${table.fields[0].propertyType} ${table.fields[0].propertyName}) {
        return ResultDto.ok(${entity?uncap_first}Service.removeById(${table.fields[0].propertyName}));
    }

}


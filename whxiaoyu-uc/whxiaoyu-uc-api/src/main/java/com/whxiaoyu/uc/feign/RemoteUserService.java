package com.whxiaoyu.uc.feign;

import com.whxiaoyu.common.core.constant.ServiceNameConstants;
import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.uc.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author jinxiaoyu
 * @date 2020/08/16 16:44
 */
@FeignClient(name = ServiceNameConstants.UC_SERVICE)
public interface RemoteUserService {

    /**
     * 获取所有用户信息
     * @return Result
     */
    @GetMapping("/sysUser/list")
    ResultDto<List<SysUser>> list();

    @PostMapping("/sysUser/getSysUser")
    ResultDto<SysUser> getSysUser(String username);
}

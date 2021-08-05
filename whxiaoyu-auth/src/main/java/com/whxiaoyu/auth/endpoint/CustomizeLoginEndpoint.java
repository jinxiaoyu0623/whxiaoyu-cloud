package com.whxiaoyu.auth.endpoint;

import com.whxiaoyu.common.core.dto.ResultDto;
import com.whxiaoyu.common.security.CustomizeUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jinxiaoyu
 */
@RestController
public class CustomizeLoginEndpoint {

    /**
     * 登录页面
     */
    @GetMapping("/auth/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @GetMapping("/")
    public ResultDto<String> index(Authentication authentication) {
        CustomizeUserDetails details = (CustomizeUserDetails) authentication.getPrincipal();
        return ResultDto.ok("welcome for you : " + details.getUserDto().getNickName());
    }
}

package com.whxiaoyu.auth.endpoint;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 自定义授权页面
 * @author jinxiaoyu
 * @date 2020/02/08 23:00
 */
@RequiredArgsConstructor
@RestController
@SessionAttributes({"authorizationRequest"})
public class CustomWhitelabelApprovalEndpoint {

    private final ClientDetailsService clientDetailsService;

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView confirm(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView("confirm");
        AuthorizationRequest authorizationRequest = (AuthorizationRequest)model.get("authorizationRequest");
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
        modelAndView.addObject("client", clientDetails.getClientId());
        modelAndView.addObject("scopes",authorizationRequest.getScope());
        return modelAndView;
    }
}

package com.whxiaoyu.cloud.auth.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 授权确认控制
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@RestController
public class CustomizeConsentController {

    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationConsentService authorizationConsentService;


    @GetMapping("/oauth2/confirm_access")
    public ModelAndView consent(Authentication authentication,
                                @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                                @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
                                @RequestParam(OAuth2ParameterNames.STATE) String state) {

        ModelAndView modelAndView = new ModelAndView("confirm");
        // Remove scopes that were already approved
        RegisteredClient registeredClient = this.registeredClientRepository.findByClientId(clientId);
        if (registeredClient == null) {
            throw new RuntimeException("无效的clientId");
        }
        //已授权过的scopes
        OAuth2AuthorizationConsent currentAuthorizationConsent =
                this.authorizationConsentService.findById(registeredClient.getId(), authentication.getName());
        Set<String> authorizedScopes;
        if (currentAuthorizationConsent != null) {
            authorizedScopes = currentAuthorizationConsent.getScopes();
        } else {
            authorizedScopes = Collections.emptySet();
        }
        //授权范围
        Set<String> scopesToApprove = new HashSet<>();
        //先前授权范围
        Set<String> previouslyApprovedScopes = new HashSet<>();
        for (String requestedScope : StringUtils.delimitedListToStringArray(scope, " ")) {
            if (authorizedScopes.contains(requestedScope)) {
                previouslyApprovedScopes.add(requestedScope);
            } else {
                scopesToApprove.add(requestedScope);
            }
        }

        modelAndView.addObject("clientId", clientId);
        modelAndView.addObject("clientName", registeredClient.getClientName());
        modelAndView.addObject("state", state);
        modelAndView.addObject("scopes", withDescription(scopesToApprove));
        modelAndView.addObject("previouslyApprovedScopes", withDescription(previouslyApprovedScopes));
        return modelAndView;
    }

    private static Set<ScopeWithDescription> withDescription(Set<String> scopes) {
        Set<ScopeWithDescription> scopeWithDescriptions = new HashSet<>();
        for (String scope : scopes) {
            scopeWithDescriptions.add(new ScopeWithDescription(scope));

        }
        return scopeWithDescriptions;
    }

    public static class ScopeWithDescription {
        private static final String DEFAULT_DESCRIPTION = "UNKNOWN SCOPE";
        private static final Map<String, String> SCOPE_DESCRIPTIONS = new HashMap<>();
        static {
            SCOPE_DESCRIPTIONS.put(
                    "openid",
                    "获得您的昵称，头像和性别"
            );
            SCOPE_DESCRIPTIONS.put(
                    "user_info",
                    "获得您的昵称，头像和性别"
            );
            SCOPE_DESCRIPTIONS.put(
                    "user_phone",
                    "获得您的手机号码"
            );
        }

        public final String scope;
        public final String description;

        ScopeWithDescription(String scope) {
            this.scope = scope;
            this.description = SCOPE_DESCRIPTIONS.getOrDefault(scope, DEFAULT_DESCRIPTION);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}

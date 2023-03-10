package com.whxiaoyu.auth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.whxiaoyu.auth.jose.Jwks;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

/**
 * ??????????????????
 *
 * @author jinxiaoyu
 */
@RequiredArgsConstructor
@Configuration
public class AuthorizationServerConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        //??????????????????
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer<>();


        //????????????
        authorizationServerConfigurer
                .authorizationEndpoint(authorizationEndpoint -> {
                    //???????????????????????????
                    authorizationEndpoint.consentPage("/oauth2/confirm_access");
                    //???????????????????????????????????????
                    authorizationEndpoint.errorResponseHandler(this::sendErrorResponse);

                });


        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = (context) -> {
            OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();
            return new OidcUserInfo(principal.getToken().getClaims());
        };

        //openId
        authorizationServerConfigurer.oidc(oidc -> {
            // ??????openId connect 1.0 scope(client.create client.read)
            // ??????????????? ???????????????????????????
            oidc.clientRegistrationEndpoint(Customizer.withDefaults());
            //????????????????????????
            oidc.userInfoEndpoint(userInfo -> userInfo.userInfoMapper(userInfoMapper));
        });

        //??????????????????????????????
        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.requestMatcher(endpointsMatcher)
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
                .apply(authorizationServerConfigurer);

        return http.build();
    }


    /**
     * ???????????????
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }


    /**
     * ?????????????????????
     */
    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                .build();
    }

    /**
     * ??????????????????
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(RegisteredClientRepository clientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, clientRepository);
    }

    /**
     * ??????????????????
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(RegisteredClientRepository clientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, clientRepository);
    }


    /**
     * ????????????
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey key = Jwks.generateLocalRsa();
        JWKSet jwkSet = new JWKSet(key);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * jwt ??????
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        OAuth2AuthorizationCodeRequestAuthenticationException authorizationCodeRequestAuthenticationException = (OAuth2AuthorizationCodeRequestAuthenticationException) exception;
        OAuth2Error error = authorizationCodeRequestAuthenticationException.getError();
        OAuth2AuthorizationCodeRequestAuthenticationToken authorizationCodeRequestAuthentication = authorizationCodeRequestAuthenticationException.getAuthorizationCodeRequestAuthentication();
        if (authorizationCodeRequestAuthentication != null && StringUtils.hasText(authorizationCodeRequestAuthentication.getRedirectUri())) {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(authorizationCodeRequestAuthentication.getRedirectUri()).queryParam("error", error.getErrorCode());
            if (StringUtils.hasText(error.getDescription())) {
                uriBuilder.queryParam("error_description", error.getDescription());
            }

            if (StringUtils.hasText(authorizationCodeRequestAuthentication.getState())) {
                uriBuilder.queryParam("state", authorizationCodeRequestAuthentication.getState());
            }
            new DefaultRedirectStrategy().sendRedirect(request, response, uriBuilder.toUriString());
        } else {
            response.sendError(HttpStatus.BAD_REQUEST.value(), error.toString());
        }
    }


}

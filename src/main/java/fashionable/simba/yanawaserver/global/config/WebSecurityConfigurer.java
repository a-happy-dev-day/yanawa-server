package fashionable.simba.yanawaserver.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.token.domain.CustomTokenDetailService;
import fashionable.simba.yanawaserver.global.authorization.AuthenticationPrincipalArgumentResolver;
import fashionable.simba.yanawaserver.global.authorization.secured.SecuredAnnotationChecker;
import fashionable.simba.yanawaserver.global.context.SecurityContextPersistenceFilter;
import fashionable.simba.yanawaserver.global.filter.JwtServerTokenAuthenticationFilter;
import fashionable.simba.yanawaserver.global.filter.JwtServerTokenAuthorizationFilter;
import fashionable.simba.yanawaserver.global.filter.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.filter.handler.DefaultAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.filter.handler.DefaultAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.global.filter.handler.TokenAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.filter.handler.TokenAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.global.provider.AuthenticationTokenProvider;
import fashionable.simba.yanawaserver.global.provider.AuthorizationManager;
import fashionable.simba.yanawaserver.global.provider.AuthorizationTokenProvider;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.token.domain.TokenDetailsService;
import fashionable.simba.yanawaserver.token.domain.ValidAccessTokenStorage;
import fashionable.simba.yanawaserver.token.domain.ValidRefreshTokenStorage;
import fashionable.simba.yanawaserver.token.domain.TokenManager;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebSecurityConfigurer implements WebMvcConfigurer {
    private final JwtPropertiesConfigurer jwtPropertiesConfigurer;
    private final UserDetailsService userDetailsService;
    private final ValidAccessTokenStorage validAccessTokenStorage;
    private final ValidRefreshTokenStorage validRefreshTokenStorage;

    public WebSecurityConfigurer(JwtPropertiesConfigurer jwtPropertiesConfigurer,
                                 UserDetailsService userDetailsService, ValidAccessTokenStorage validAccessTokenStorage, ValidRefreshTokenStorage validRefreshTokenStorage) {
        this.jwtPropertiesConfigurer = jwtPropertiesConfigurer;
        this.userDetailsService = userDetailsService;
        this.validAccessTokenStorage = validAccessTokenStorage;
        this.validRefreshTokenStorage = validRefreshTokenStorage;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityContextPersistenceFilter());
        registry.addInterceptor(serverTokenAuthenticationFilter()).addPathPatterns("/login/token");
        registry.addInterceptor(serverTokenAuthorizationFilter());
    }

    private JwtServerTokenAuthorizationFilter serverTokenAuthorizationFilter() {
        return new JwtServerTokenAuthorizationFilter(defaultAuthenticationSuccessHandler(), failureHandler(), authorizationTokenProvider());
    }

    private JwtServerTokenAuthenticationFilter serverTokenAuthenticationFilter() {
        return new JwtServerTokenAuthenticationFilter(tokenAuthenticationSuccessHandler(), loginAuthenticationFailureHandler(), authenticationTokenProvider(), objectMapper());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    AuthorizationManager authenticationTokenProvider() {
        return new AuthenticationTokenProvider(jwtTokenProvider(), userDetailsService);
    }

    @Bean
    TokenManager tokenManager() {
        return new TokenManager(validAccessTokenStorage, validRefreshTokenStorage, jwtTokenProvider());
    }

    @Bean
    TokenDetailsService tokenDetailsService() {
        return new CustomTokenDetailService(tokenManager());
    }

    @Bean
    AuthorizationManager authorizationTokenProvider() {
        return new AuthorizationTokenProvider(jwtTokenProvider(), tokenDetailsService());
    }

    @Bean
    TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler() {
        return new TokenAuthenticationSuccessHandler(objectMapper(), jwtTokenProvider(), tokenManager());
    }

    @Bean
    DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler() {
        return new DefaultAuthenticationSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return new DefaultAuthenticationFailureHandler();
    }

    @Bean
    TokenAuthenticationFailureHandler loginAuthenticationFailureHandler() {
        return new TokenAuthenticationFailureHandler();
    }

    @Bean
    JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(
            jwtPropertiesConfigurer.getSecretKey(),
            jwtPropertiesConfigurer.getRefreshKey(),
            jwtPropertiesConfigurer.getValidityRefreshTokenMilliseconds(),
            jwtPropertiesConfigurer.getValidityAccessTokenMilliseconds()
        );
    }

    @Bean
    SecuredAnnotationChecker securedAnnotationChecker() {
        return new SecuredAnnotationChecker();
    }

}

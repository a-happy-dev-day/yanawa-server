package fashionable.simba.yanawaserver.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.authorization.AuthenticationPrincipalArgumentResolver;
import fashionable.simba.yanawaserver.auth.authorization.secured.SecuredAnnotationChecker;
import fashionable.simba.yanawaserver.auth.context.SecurityContextPersistenceFilter;
import fashionable.simba.yanawaserver.auth.filter.ServerTokenAuthenticationFilter;
import fashionable.simba.yanawaserver.auth.filter.ServerTokenAuthorizationFilter;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.DefaultAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.DefaultAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.handler.TokenAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.TokenAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationTokenProvider;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationTokenProvider;
import fashionable.simba.yanawaserver.auth.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebSecurityConfigurer implements WebMvcConfigurer {
    private final String secretKey;
    private final long validityInMilliseconds;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfigurer(@Value("${security.jwt.token.secret-key}") String secretKey,
                                 @Value("${security.jwt.token.expire-length}") long validityInMilliseconds,
                                 UserDetailsService userDetailsService) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityContextPersistenceFilter());
        registry.addInterceptor(serverTokenAuthenticationFilter()).addPathPatterns("/login/token");
        registry.addInterceptor(serverTokenAuthorizationFilter());
    }

    private ServerTokenAuthorizationFilter serverTokenAuthorizationFilter() {
        return new ServerTokenAuthorizationFilter(defaultAuthenticationSuccessHandler(), failureHandler(), authorizationTokenProvider());
    }

    private ServerTokenAuthenticationFilter serverTokenAuthenticationFilter() {
        return new ServerTokenAuthenticationFilter(tokenAuthenticationSuccessHandler(), loginAuthenticationFailureHandler(), authenticationTokenProvider(), objectMapper());
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
        return new AuthenticationTokenProvider(jwtTokenProvider());
    }

    @Bean
    AuthorizationManager authorizationTokenProvider() {
        return new AuthorizationTokenProvider(jwtTokenProvider());
    }

    @Bean
    TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler() {
        return new TokenAuthenticationSuccessHandler(objectMapper(), jwtTokenProvider());
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
        return new JwtTokenProvider(secretKey, validityInMilliseconds);
    }

    @Bean
    SecuredAnnotationChecker securedAnnotationChecker() {
        return new SecuredAnnotationChecker();
    }

}

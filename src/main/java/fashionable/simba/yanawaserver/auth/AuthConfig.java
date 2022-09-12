package fashionable.simba.yanawaserver.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.authorization.AuthenticationPrincipalArgumentResolver;
import fashionable.simba.yanawaserver.auth.authorization.secured.SecuredAnnotationChecker;
import fashionable.simba.yanawaserver.auth.context.SecurityContextPersistenceFilter;
import fashionable.simba.yanawaserver.auth.filter.ServerTokenAuthenticationFilter;
import fashionable.simba.yanawaserver.auth.filter.ServerTokenAuthorizationFilter;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.handler.DefaultAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.DefaultAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.handler.LoginAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.TokenAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationClient;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthorizationClient;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.auth.provider.TokenAuthenticationProvider;
import fashionable.simba.yanawaserver.auth.provider.UserDetailsAuthenticationProvider;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    private final String secretKey;
    private final long validityInMilliseconds;
    private final UserDetailsService userDetailsService;

    public AuthConfig(@Value("${security.jwt.token.secret-key}") String secretKey,
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
        return new ServerTokenAuthorizationFilter(successHandler(), failureHandler(), tokenAuthenticationProvider());
    }

    private ServerTokenAuthenticationFilter serverTokenAuthenticationFilter() {
        return new ServerTokenAuthenticationFilter(tokenAuthenticationSuccessHandler(), loginFailureHandler(), userDetailsAuthenticationProvider(), objectMapper());
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
    AuthenticationManager userDetailsAuthenticationProvider() {
        return new UserDetailsAuthenticationProvider(userDetailsService);
    }

    @Bean
    AuthenticationManager tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider(jwtTokenProvider());
    }

    @Bean
    TokenAuthenticationSuccessHandler tokenAuthenticationSuccessHandler() {
        return new TokenAuthenticationSuccessHandler(objectMapper(), jwtTokenProvider());
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return new DefaultAuthenticationSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return new DefaultAuthenticationFailureHandler();
    }

    @Bean
    AuthenticationFailureHandler loginFailureHandler() {
        return new LoginAuthenticationFailureHandler();
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

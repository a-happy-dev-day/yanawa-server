package fashionable.simba.yanawaserver.auth;

import fashionable.simba.yanawaserver.auth.authentication.filter.BearerTokenAuthenticationFilter;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.DefaultAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.DefaultAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.LoginAuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.TokenAuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.authentication.provider.TokenAuthenticationProvider;
import fashionable.simba.yanawaserver.auth.authentication.provider.UserDetailsAuthenticationProvider;
import fashionable.simba.yanawaserver.auth.authorization.AuthenticationPrincipalArgumentResolver;
import fashionable.simba.yanawaserver.auth.authorization.secured.SecuredAnnotationChecker;
import fashionable.simba.yanawaserver.auth.context.SecurityContextPersistenceFilter;
import fashionable.simba.yanawaserver.auth.token.JwtTokenProvider;
import fashionable.simba.yanawaserver.auth.token.TokenAuthenticationInterceptor;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;
    private final UserDetailsService userDetailsService;

    public AuthConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityContextPersistenceFilter());
        registry.addInterceptor(new TokenAuthenticationInterceptor(tokenAuthenticationSuccessHandler(), loginFailureHandler(), userDetailsAuthenticationProvider())).addPathPatterns("/login/token");
        registry.addInterceptor(new BearerTokenAuthenticationFilter(successHandler(), failureHandler(), tokenAuthenticationProvider()));
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
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
        return new TokenAuthenticationSuccessHandler(jwtTokenProvider());
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

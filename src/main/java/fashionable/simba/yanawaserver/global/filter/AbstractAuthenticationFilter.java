package fashionable.simba.yanawaserver.global.filter;

import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.context.SecurityContextHolder;
import fashionable.simba.yanawaserver.global.filter.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.filter.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.global.jwt.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.global.jwt.provider.AuthorizationManager;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractAuthenticationFilter implements HandlerInterceptor {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final AuthorizationManager authenticationManager;

    protected AbstractAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthorizationManager authenticationManager) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            Authentication authentication = attemptAuthentication(request);
            successfulAuthentication(request, response, authentication);
            return getContinueChainBeforeSuccessfulAuthentication();
        } catch (Exception e) {
            unsuccessfulAuthentication(request, response, e);
            return getContinueChainBeforeUnsuccessfulAuthentication();
        }
    }

    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

    private void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Exception e) {
        failureHandler.onAuthenticationFailure(request, response, e);
    }


    private Authentication attemptAuthentication(HttpServletRequest request) throws IOException {
        AuthenticationToken token = convert(request);
        return authenticationManager.authenticate(token);
    }

    protected abstract AuthenticationToken convert(HttpServletRequest request) throws IOException;

    protected boolean getContinueChainBeforeSuccessfulAuthentication() {
        return true;
    }

    protected boolean getContinueChainBeforeUnsuccessfulAuthentication() {
        return true;
    }

}

package fashionable.simba.yanawaserver.auth.authentication.filter;

import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractAuthenticationFilter implements HandlerInterceptor {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final AuthenticationManager authenticationManager;

    public AbstractAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Authentication authentication = attemptAuthentication(request, response);
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


    private Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

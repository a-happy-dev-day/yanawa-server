package fashionable.simba.yanawaserver.auth.handler;

import fashionable.simba.yanawaserver.auth.context.SecurityContextHolder;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        SecurityContextHolder.clearContext();
    }
}

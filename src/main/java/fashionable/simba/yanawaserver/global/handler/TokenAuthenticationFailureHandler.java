package fashionable.simba.yanawaserver.global.handler;

import fashionable.simba.yanawaserver.global.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        SecurityContextHolder.clearContext();
    }
}

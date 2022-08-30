package fashionable.simba.yanawaserver.auth.authentication.handler;


import fashionable.simba.yanawaserver.auth.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        SecurityContextHolder.clearContext();
    }
}
package fashionable.simba.yanawaserver.global.filter.handler;

import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        SecurityContextHolder.getContext().setAuthentication(Authentication.ANONYMOUS);
    }
}

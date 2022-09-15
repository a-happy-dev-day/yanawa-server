package fashionable.simba.yanawaserver.auth.handler;

import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.context.SecurityContext;
import fashionable.simba.yanawaserver.auth.context.SecurityContextHolder;
import fashionable.simba.yanawaserver.members.domain.RoleType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        SecurityContextHolder.getContext().setAuthentication(Authentication.ANONYMOUS);
    }
}

package fashionable.simba.yanawaserver.auth.handler;

import fashionable.simba.yanawaserver.auth.context.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.debug("Success login username is {} and authorities is {}", authentication.getPrincipal(), authentication.getAuthorities());
    }
}

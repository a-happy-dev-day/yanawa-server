package fashionable.simba.yanawaserver.auth.authentication.handler;

import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception failed) {
        throw new AuthenticationException("인증에 실패했습니다.");
    }
}

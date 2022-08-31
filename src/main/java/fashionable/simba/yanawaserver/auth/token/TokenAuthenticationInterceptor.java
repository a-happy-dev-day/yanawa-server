package fashionable.simba.yanawaserver.auth.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.authentication.filter.AbstractAuthenticationFilter;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class TokenAuthenticationInterceptor extends AbstractAuthenticationFilter {
    public TokenAuthenticationInterceptor(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager) {
        super(successHandler, failureHandler, authenticationManager);
    }

    @Override
    protected AuthenticationToken convert(HttpServletRequest request) throws IOException {
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        TokenRequest tokenRequest = new ObjectMapper().readValue(content, TokenRequest.class);

        String principal = tokenRequest.getUsername();

        // 카카오톡에서 토큰을 발급받았다!
        // 카카오톡 토큰이 유효한지 검사한다.
        // 카카오톡에서 데이터를 가져왔다!
        // 데이터에서 이메일을 가져와 기존 사용자인지 확인한다.
        // 아니면 회원가입을 한다.
        // 회원가입을 하면서 카카오톡의 토큰과 리프레시 토큰을 저장한다.
        // 우리 서버의 토큰을 발급한다.

        return new AuthenticationToken(principal);
    }

    @Override
    protected boolean getContinueChainBeforeSuccessfulAuthentication() {
        return false;
    }

    @Override
    protected boolean getContinueChainBeforeUnsuccessfulAuthentication() {
        return false;
    }
}

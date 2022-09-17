package fashionable.simba.yanawaserver.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationToken;
import fashionable.simba.yanawaserver.auth.ui.TokenRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class ServerTokenAuthenticationFilter extends AbstractAuthenticationFilter {
    private final ObjectMapper mapper;

    public ServerTokenAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthorizationManager authenticationManager, ObjectMapper mapper) {
        super(successHandler, failureHandler, authenticationManager);
        this.mapper = mapper;
    }

    @Override
    protected AuthorizationToken convert(HttpServletRequest request) throws IOException {
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        // TODO 인증 토큰 메시지는 암호화가 되어 만들어진다.
        TokenRequest tokenRequest = mapper.readValue(content, TokenRequest.class);
        return new AuthorizationToken(tokenRequest.getUsername());
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

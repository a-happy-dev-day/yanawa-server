package fashionable.simba.yanawaserver.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.dto.TokenRequest;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class ServerTokenAuthenticationFilter extends AbstractAuthenticationFilter {
    private final ObjectMapper mapper;

    public ServerTokenAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager, ObjectMapper mapper) {
        super(successHandler, failureHandler, authenticationManager);
        this.mapper = mapper;
    }

    @Override
    protected AuthenticationToken convert(HttpServletRequest request) throws IOException {
        // TODO : 문서에 맞게 수정
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        TokenRequest tokenRequest = mapper.readValue(content, TokenRequest.class);

        String principal = tokenRequest.getUsername();

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

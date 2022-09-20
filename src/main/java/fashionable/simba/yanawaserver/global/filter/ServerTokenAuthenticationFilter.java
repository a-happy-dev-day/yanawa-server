package fashionable.simba.yanawaserver.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.global.domain.AuthenticationCode;
import fashionable.simba.yanawaserver.global.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.global.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.global.provider.AuthorizationManager;

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
    protected AuthenticationToken convert(HttpServletRequest request) throws IOException {
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        AuthenticationCode tokenRequest = mapper.readValue(content, AuthenticationCode.class);
        return new AuthenticationToken(tokenRequest.getAccessCode());
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

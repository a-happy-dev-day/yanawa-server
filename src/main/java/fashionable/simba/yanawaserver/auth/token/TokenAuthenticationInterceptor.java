package fashionable.simba.yanawaserver.auth.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.authentication.filter.AbstractAuthenticationFilter;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;

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

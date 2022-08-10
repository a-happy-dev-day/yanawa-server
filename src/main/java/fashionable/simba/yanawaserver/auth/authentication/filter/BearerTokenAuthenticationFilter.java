package fashionable.simba.yanawaserver.auth.authentication.filter;


import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.authentication.AuthorizationExtractor;
import fashionable.simba.yanawaserver.auth.authentication.AuthorizationType;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.authentication.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.authentication.provider.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;

public class BearerTokenAuthenticationFilter extends AbstractAuthenticationFilter {

    public BearerTokenAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager) {
        super(successHandler, failureHandler, authenticationManager);
    }

    @Override
    protected AuthenticationToken convert(HttpServletRequest request) {
        String authCredentials = AuthorizationExtractor.extract(request, AuthorizationType.BEARER);
        return new AuthenticationToken(authCredentials);
    }
}

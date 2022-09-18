package fashionable.simba.yanawaserver.auth.filter;


import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationExtractor;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationType;

import javax.servlet.http.HttpServletRequest;

public class ServerTokenAuthorizationFilter extends AbstractAuthenticationFilter {

    public ServerTokenAuthorizationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthorizationManager authenticationManager) {
        super(successHandler, failureHandler, authenticationManager);
    }

    /**
     * 토큰 정보를 가져와 권한을 확인한다. *
     *
     * @param request current HTTP request
     * @return AuthenticationToken
     */
    @Override
    protected AuthenticationToken convert(HttpServletRequest request) {
        String authCredentials = AuthorizationExtractor.extract(request, AuthorizationType.BEARER);
        return new AuthenticationToken(authCredentials);
    }
}

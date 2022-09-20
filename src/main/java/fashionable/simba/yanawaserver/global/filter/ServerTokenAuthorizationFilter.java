package fashionable.simba.yanawaserver.global.filter;


import fashionable.simba.yanawaserver.global.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.global.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.global.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.global.provider.AuthorizationExtractor;
import fashionable.simba.yanawaserver.global.provider.AuthorizationManager;
import fashionable.simba.yanawaserver.global.provider.AuthorizationType;

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

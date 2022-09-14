package fashionable.simba.yanawaserver.auth.filter;


import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationExtractor;
import fashionable.simba.yanawaserver.auth.provider.AuthorizationType;

import javax.servlet.http.HttpServletRequest;

public class ServerTokenAuthorizationFilter extends AbstractAuthenticationFilter {

    public ServerTokenAuthorizationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager) {
        super(successHandler, failureHandler, authenticationManager);
    }

    /**
     * 토큰 정보를 가져와 권한을 확인한다. *
     * 토큰 정보가 없으면 익명의 사용자(`Anonymous`)로 판단한다.*
     * 유효하지 않은 토큰이면 익명의 사용자(`Anonymous`)로 판단한다.*
     * 토큰에서 사용자의 권한을 확인한다.*
     * 토큰의 만료시간을 확인해 10 분 미만이면 토큰을 재발급해서 새로운 토큰을 발급한다.*
     *
     * @param request current HTTP request
     * @return AuthenticationToken
     */
    @Override
    protected AuthenticationToken convert(HttpServletRequest request) {
        // TODO 사용자 토큰 유효시간을 갱신한다.
        String authCredentials = AuthorizationExtractor.extract(request, AuthorizationType.BEARER);
        return new AuthenticationToken(authCredentials);
    }
}

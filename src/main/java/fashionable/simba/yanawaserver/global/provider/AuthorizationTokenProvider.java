package fashionable.simba.yanawaserver.global.provider;


import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.token.TokenDetailsService;

import java.util.List;

public class AuthorizationTokenProvider implements AuthorizationManager {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenDetailsService tokenDetailsService;

    public AuthorizationTokenProvider(JwtTokenProvider jwtTokenProvider, TokenDetailsService tokenDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenDetailsService = tokenDetailsService;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        if (!jwtTokenProvider.validateToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException("토큰이 유효하지 않습니다.");
        }

        if (!tokenDetailsService.validateAccessToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException("토큰이 유효하지 않습니다. - In invalid access token storage");
        }

        String principal = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        List<String> roles = jwtTokenProvider.getRoles(authenticationToken.getPrincipal());

        return new Authentication(principal, roles);
    }
}

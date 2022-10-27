package fashionable.simba.yanawaserver.global.provider;


import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.token.domain.TokenManager;

import java.util.List;

public class AuthorizationTokenProvider implements AuthorizationManager {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenManager tokenManager;

    public AuthorizationTokenProvider(JwtTokenProvider jwtTokenProvider, TokenManager tokenManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenManager = tokenManager;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        tokenManager.verifyAccessToken(authenticationToken.getPrincipal());

        String principal = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        List<String> roles = jwtTokenProvider.getRoles(authenticationToken.getPrincipal());

        return new Authentication(principal, roles);
    }
}

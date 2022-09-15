package fashionable.simba.yanawaserver.auth.provider;


import fashionable.simba.yanawaserver.auth.context.Authentication;

import java.util.List;

public class TokenAuthenticationProvider implements AuthenticationManager {
    private final JwtTokenProvider jwtTokenProvider;

    public TokenAuthenticationProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        if (!jwtTokenProvider.validateToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException("토큰이 유효하지 않습니다.");
        }

        String principal = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        List<String> roles = jwtTokenProvider.getRoles(authenticationToken.getPrincipal());

        return new Authentication(principal, roles);
    }
}

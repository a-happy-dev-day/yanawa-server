package fashionable.simba.yanawaserver.auth.authentication.provider;


import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.token.JwtTokenProvider;

import java.util.List;

public class TokenAuthenticationProvider implements AuthenticationManager {
    private JwtTokenProvider jwtTokenProvider;

    public TokenAuthenticationProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        if (!jwtTokenProvider.validateToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException();
        }

        String principal = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        List<String> roles = jwtTokenProvider.getRoles(authenticationToken.getPrincipal());

        Authentication authentication = new Authentication(principal, roles);
        return authentication;
    }
}

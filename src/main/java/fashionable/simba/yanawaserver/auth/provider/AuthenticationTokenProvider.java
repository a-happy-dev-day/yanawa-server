package fashionable.simba.yanawaserver.auth.provider;


import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;

public class AuthenticationTokenProvider implements AuthorizationManager {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public AuthenticationTokenProvider(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticate(AuthorizationToken authenticationToken) {
        if (!jwtTokenProvider.validateToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException("토큰이 유효하지 않습니다.");
        }

        String username = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new Authentication(userDetails.getUsername(), userDetails.getAuthorities());
    }
}

package fashionable.simba.yanawaserver.global.provider;


import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.userdetails.UserDetails;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;

public class AuthenticationProvider implements AuthenticationManager {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    public AuthenticationProvider(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        if (!jwtTokenProvider.validateToken(authenticationToken.getPrincipal())) {
            throw new AuthenticationException("토큰이 유효하지 않습니다.");
        }

        String username = jwtTokenProvider.getPrincipal(authenticationToken.getPrincipal());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new Authentication(userDetails.getUsername(), userDetails.getAuthorities());
    }
}

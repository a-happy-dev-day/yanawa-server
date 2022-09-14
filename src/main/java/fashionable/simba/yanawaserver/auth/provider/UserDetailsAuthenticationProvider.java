package fashionable.simba.yanawaserver.auth.provider;


import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;

public class UserDetailsAuthenticationProvider implements AuthenticationManager {
    private final UserDetailsService userDetailsService;

    public UserDetailsAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticate(AuthenticationToken authenticationToken) {
        String principal = authenticationToken.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        checkAuthentication(userDetails);
        return new Authentication(userDetails.getUsername(), userDetails.getAuthorities());
    }

    private void checkAuthentication(UserDetails userDetails) {
        if (userDetails == null) {
            throw new AuthenticationException();
        }
    }
}
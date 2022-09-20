package fashionable.simba.yanawaserver.auth.ui;

import fashionable.simba.yanawaserver.auth.domain.TokenManagementService;
import fashionable.simba.yanawaserver.global.authorization.AuthenticationPrincipal;
import fashionable.simba.yanawaserver.global.authorization.secured.Secured;
import fashionable.simba.yanawaserver.global.provider.AuthenticationException;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.token.AuthorizationAccessToken;
import fashionable.simba.yanawaserver.global.token.AuthorizationRefreshToken;
import fashionable.simba.yanawaserver.global.userdetails.UserDetails;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class TokenController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final TokenManagementService tokenManagementService;

    public TokenController(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService, TokenManagementService tokenManagementService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.tokenManagementService = tokenManagementService;
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthorizationAccessToken> refreshToken(@RequestBody AuthorizationRefreshToken refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken.getRefreshToken())) {
            throw new AuthenticationException("Invalid Refresh Token");
        }

        String username = jwtTokenProvider.getPrincipalByRefreshToken(refreshToken.getRefreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(new AuthorizationAccessToken(jwtTokenProvider.createAuthorizationToken(username, userDetails.getAuthorities())));
    }

    @PostMapping("expire/access")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_TEST"})
    public ResponseEntity<Void> expireAccess(@RequestBody AuthorizationAccessToken accessToken) {
        tokenManagementService.expireAccessToken(accessToken.getAccessToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("expire/refresh")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_TEST"})
    public ResponseEntity<Void> expireRefresh(@AuthenticationPrincipal AuthorizationRefreshToken refreshToken) {
        tokenManagementService.expireRefreshToken(refreshToken.getRefreshToken());
        return ResponseEntity.ok().build();
    }
}

package fashionable.simba.yanawaserver.global.token.ui;

import fashionable.simba.yanawaserver.global.authorization.secured.Secured;
import fashionable.simba.yanawaserver.global.provider.AuthenticationException;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.token.domain.AuthorizationAccessToken;
import fashionable.simba.yanawaserver.global.token.domain.AuthorizationRefreshToken;
import fashionable.simba.yanawaserver.global.token.domain.TokenDetailsService;
import fashionable.simba.yanawaserver.global.token.domain.TokenManager;
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
    private final TokenDetailsService tokenDetailsService;
    private final TokenManager tokenManager;

    public TokenController(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService, TokenDetailsService tokenDetailsService, TokenManager tokenManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.tokenDetailsService = tokenDetailsService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthorizationAccessToken> refreshToken(@RequestBody AuthorizationRefreshToken refreshToken) {
        tokenDetailsService.validateRefreshToken(refreshToken.getRefreshToken());

        String username = jwtTokenProvider.getPrincipalByRefreshToken(refreshToken.getRefreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        AuthorizationAccessToken accessToken = new AuthorizationAccessToken(jwtTokenProvider.createAuthorizationToken(username, userDetails.getAuthorities()));
        tokenManager.manageAccessToken(accessToken.getAccessToken());
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("expire/access")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_TEST"})
    public ResponseEntity<Void> expireAccess(@RequestBody AuthorizationAccessToken accessToken) {
        tokenManager.expireAccessToken(accessToken.getAccessToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("expire/refresh")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_TEST"})
    public ResponseEntity<Void> expireRefresh(@RequestBody AuthorizationRefreshToken refreshToken) {
        tokenManager.expireRefreshToken(refreshToken.getRefreshToken());
        return ResponseEntity.ok().build();
    }
}

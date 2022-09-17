package fashionable.simba.yanawaserver.auth.ui;

import fashionable.simba.yanawaserver.auth.authorization.AuthenticationPrincipal;
import fashionable.simba.yanawaserver.auth.authorization.secured.Secured;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationException;
import fashionable.simba.yanawaserver.auth.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final KakaoAuthenticationService kakaoAuthenticationService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(KakaoAuthenticationService kakaoAuthenticationService, UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.kakaoAuthenticationService = kakaoAuthenticationService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    /**
     * 카카오 로그인 페이지로 이동
     *
     * @return
     */
    @GetMapping("kakao/login")
    public ResponseEntity<Void> loginPage() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION, kakaoAuthenticationService.getLoginUri()).build();
    }

    /**
     * 카카오 로그인 페이지에서 로그인을 하면 사용자 정보를 저장한다.*
     * 사용자의 정보를 이용해 토큰을 발급한다.*
     *
     * @param accessCode
     * @return
     */
    @GetMapping("kakao/login/callback")
    public ResponseEntity<TokenRequest> loginCallback(String accessCode) {
        KakaoMember kakaoMember = kakaoAuthenticationService.getUserInfo(kakaoAuthenticationService.getAccessToken(accessCode));
        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);
        return ResponseEntity.ok(new TokenRequest((String) userDetails.getUsername()));
    }


    @PostMapping("login")
    public ResponseEntity<TokenRequest> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getPassword() == null || !loginRequest.getPassword().equals("password-admin")) {
            throw new AuthenticationException();
        }

        if (!userDetailsService.isValidUser(loginRequest.getUsername())) {
            throw new AuthenticationException();
        }

        return ResponseEntity.ok(new TokenRequest(jwtTokenProvider.createAuthenticationToken(loginRequest.getUsername())));
    }

    @GetMapping("/refresh")
    @Secured(value = {"ROLE_ADMIN", "ROLE_MEMBER"})
    public ResponseEntity<Void> refreshToken(@AuthenticationPrincipal User user, String refreshToken) {
        if (!userDetailsService.isValidRefreshToken(user.getUsername(), refreshToken)) {
            throw new AuthenticationException();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("logout")
    @Secured(value = {"ROLE_MEMBER"})
    public ResponseEntity<Void> logout(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().build();
    }
}

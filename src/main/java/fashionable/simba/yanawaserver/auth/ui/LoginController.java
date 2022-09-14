package fashionable.simba.yanawaserver.auth.ui;

import fashionable.simba.yanawaserver.auth.dto.TokenResponse;
import fashionable.simba.yanawaserver.auth.filter.AccessCode;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/kakao/login")
    public ResponseEntity<Void> loginPage() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION, kakaoAuthenticationService.getLoginUri()).build();
    }

    @GetMapping("/kakao/login/callback")
    public ResponseEntity<TokenResponse> loginCallback(String code) {
        AccessToken accessToken = kakaoAuthenticationService.getAccessToken(new AccessCode(code));
        KakaoMember kakaoMember = kakaoAuthenticationService.getUserInfo((KakaoAccessToken) accessToken);
        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);

        String token = jwtTokenProvider.createToken(userDetails.getUsername().toString(), userDetails.getAuthorities());
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(tokenResponse);
    }

}

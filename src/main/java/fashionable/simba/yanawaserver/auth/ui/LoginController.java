package fashionable.simba.yanawaserver.auth.ui;

import fashionable.simba.yanawaserver.auth.dto.TokenRequest;
import fashionable.simba.yanawaserver.auth.filter.AccessCode;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao/login")
public class LoginController {
    private final KakaoAuthenticationService kakaoAuthenticationService;
    private final UserDetailsService userDetailsService;

    public LoginController(KakaoAuthenticationService kakaoAuthenticationService, UserDetailsService userDetailsService) {
        this.kakaoAuthenticationService = kakaoAuthenticationService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public ResponseEntity<Void> loginPage() {
        return ResponseEntity.status(HttpStatus.SEE_OTHER).header(HttpHeaders.LOCATION, kakaoAuthenticationService.getLoginUri()).build();
    }

    /**
     * 카카오 로그인 페이지에서 로그인을 하면 사용자 정보를 저장한다.*
     * `principal`을 반환한다.*
     * `/login/token`에서 사용자의 정보를 조회하고 토큰을 발급한다.*
     *
     * @param code
     * @return
     */
    @GetMapping("callback")
    public ResponseEntity<TokenRequest> loginCallback(String code) {
        AccessToken accessToken = kakaoAuthenticationService.getAccessToken(new AccessCode(code));
        KakaoMember kakaoMember = kakaoAuthenticationService.getUserInfo((KakaoAccessToken) accessToken);
        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);
        return ResponseEntity.ok(new TokenRequest(userDetails.getUsername()));
    }
}

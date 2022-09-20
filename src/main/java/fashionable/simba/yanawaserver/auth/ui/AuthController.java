package fashionable.simba.yanawaserver.auth.ui;

import fashionable.simba.yanawaserver.auth.exception.AccessCodeException;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.ui.dto.LoginRequest;
import fashionable.simba.yanawaserver.auth.ui.dto.TokenRequest;
import fashionable.simba.yanawaserver.global.authorization.AuthenticationPrincipal;
import fashionable.simba.yanawaserver.global.authorization.secured.Secured;
import fashionable.simba.yanawaserver.global.token.AuthorizationAccessToken;
import fashionable.simba.yanawaserver.global.token.AuthorizationRefreshToken;
import fashionable.simba.yanawaserver.global.provider.AuthenticationException;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.userdetails.User;
import fashionable.simba.yanawaserver.global.userdetails.UserDetails;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final KakaoAuthenticationService kakaoAuthenticationService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(KakaoAuthenticationService kakaoAuthenticationService, UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
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
     * @param code
     * @return
     */
    @GetMapping("kakao/login/callback")
    public ResponseEntity<TokenRequest> loginCallback(@RequestParam(required = false) String code,
                                                      @RequestParam(required = false) String error,
                                                      @RequestParam(required = false) String errorDescription) {
        if (error != null) {
            throw new AccessCodeException("코드를 발급받는 곳에서 문제가 발생했습니다.");
        }

        KakaoMember kakaoMember = kakaoAuthenticationService.getUserInfo(kakaoAuthenticationService.getAccessToken(code));
        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);
        return ResponseEntity.ok(new TokenRequest(jwtTokenProvider.createAuthenticationToken((String) userDetails.getUsername())));
    }


    @PostMapping("login")
    public ResponseEntity<TokenRequest> login(@RequestBody LoginRequest loginRequest) {
        if (!userDetailsService.isValidUser(loginRequest.getUsername())) {
            throw new AuthenticationException();
        }

        if (loginRequest.getPassword() == null || !loginRequest.getPassword().equals("password-admin")) {
            throw new AuthenticationException();
        }

        return ResponseEntity.ok(new TokenRequest(jwtTokenProvider.createAuthenticationToken(loginRequest.getUsername())));
    }

}

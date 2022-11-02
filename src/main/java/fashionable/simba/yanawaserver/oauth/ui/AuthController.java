package fashionable.simba.yanawaserver.oauth.ui;

import fashionable.simba.yanawaserver.global.provider.AuthenticationException;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.userdetails.UserDetails;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.oauth.service.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.oauth.ui.dto.LoginRequest;
import fashionable.simba.yanawaserver.oauth.ui.dto.TokenDto;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final KakaoAuthenticationService kakaoAuthenticationService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(KakaoAuthenticationService kakaoAuthenticationService, UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.kakaoAuthenticationService = kakaoAuthenticationService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("login/kakao")
    public ResponseEntity<Void> loginPage() {
        log.info("Request Page");
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
            .header(HttpHeaders.LOCATION, kakaoAuthenticationService.getLoginUri()).build();
    }

    /**
     * 카카오 로그인 페이지에서 로그인을 하면 사용자 정보를 저장한다.*
     * 사용자의 정보를 이용해 토큰을 발급한다.*
     *
     * @param code
     * @return ResponseEntity
     */
    @PostMapping("login/kakao")
    public ResponseEntity<TokenDto> login(@RequestBody TokenDto code) {
        final String accessCode = code.getAccessCode();

        log.info("Request kakao social login code is {}", accessCode);
        KakaoMember kakaoMember = kakaoAuthenticationService.getUserInfo(kakaoAuthenticationService.getAccessToken(accessCode));
        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);
        return ResponseEntity.ok(new TokenDto(jwtTokenProvider.createAccessCode((String) userDetails.getUsername())));
    }

    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        final String username = loginRequest.getUsername();

        log.info("Request login server username is {}", username);
        if (!userDetailsService.isValidUser(username)) {
            throw new AuthenticationException();
        }

        if (loginRequest.getPassword() == null || !loginRequest.getPassword().equals("password-admin")) {
            throw new AuthenticationException();
        }

        return ResponseEntity.ok(new TokenDto(jwtTokenProvider.createAccessCode(username)));
    }

}

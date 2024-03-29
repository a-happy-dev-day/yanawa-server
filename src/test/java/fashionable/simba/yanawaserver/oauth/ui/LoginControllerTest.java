package fashionable.simba.yanawaserver.oauth.ui;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.userdetails.User;
import fashionable.simba.yanawaserver.global.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.oauth.infra.dto.KakaoAccessToken;
import fashionable.simba.yanawaserver.oauth.service.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.oauth.ui.dto.TokenDto;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    private static final KakaoMember KAKAO_MEMBER = new KakaoMember(
        1234L,
        "email@email.com",
        List.of(RoleType.ROLE_MEMBER.name()),
        123L,
        "username",
        "profile.png",
        "thumbnail.jpg",
        true);
    private static final KakaoAccessToken KAKAO_ACCESS_TOKEN = new KakaoAccessToken(
        "Bearer",
        "access token",
        "refresh Token");
    private AuthController loginController;
    @Mock
    private KakaoAuthenticationService kakaoAuthenticationService;
    @Mock
    private UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("secret-key", "refresh-key", 100000, 100000);

    @BeforeEach
    void setUp() {
        loginController = new AuthController(kakaoAuthenticationService, userDetailsService, jwtTokenProvider);
    }

    @Test
    @DisplayName("카카오 로그인 인증 코드를 받아 사용자 정보를 가져온다.")
    void kakao_login_auth() {
        KakaoAccessToken accessToken = KAKAO_ACCESS_TOKEN;
        KakaoMember kakaoMember = KAKAO_MEMBER;

        User user = new User("1234", List.of(RoleType.ROLE_MEMBER.name()));

        when(kakaoAuthenticationService.getAccessToken(any())).thenReturn(accessToken);
        when(kakaoAuthenticationService.getUserInfo(accessToken)).thenReturn(kakaoMember);
        when(userDetailsService.saveKakaoMember(kakaoMember)).thenReturn(user);

        ResponseEntity<TokenDto> response = loginController.login(new TokenDto("accessCode"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}

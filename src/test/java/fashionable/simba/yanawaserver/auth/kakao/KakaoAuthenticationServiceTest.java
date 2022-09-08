package fashionable.simba.yanawaserver.auth.kakao;

import fashionable.simba.yanawaserver.auth.filter.AccessCode;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KakaoAuthenticationServiceTest {
    private static final String ACCESS_TOKEN = "access-token";
    private static final KakaoAccessToken KAKAO_ACCESS_TOKEN = new KakaoAccessToken("bearer", ACCESS_TOKEN, new Date(), "refresh-token", new Date());
    @Mock
    private KakaoAuthenticationClient authenticationClient;
    @Mock
    private KakaoAuthorizationClient authorizationClient;

    private KakaoAuthenticationService kakaoAuthenticationService;

    @BeforeEach
    void setUp() {
        kakaoAuthenticationService = new KakaoAuthenticationService(
            authenticationClient,
            authorizationClient,
            "redirect-uri",
            "client-id",
            "secret-key");
    }

    @Test
    @DisplayName("AccessToken을 조회한다.")
    void getAccessToken() {
        // givne
        AccessCode accessCode = new AccessCode("access-code");

        // when
        when(
            authenticationClient.getToken(
                MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                "authorization_code",
                "client-id",
                "redirect-uri",
                accessCode.getAccessCode(),
                "secret-key")
        ).thenReturn(ResponseEntity.ok(KAKAO_ACCESS_TOKEN));

        // then
        AccessToken token = kakaoAuthenticationService.getAccessToken(accessCode);
        assertThat(token.getAccessToken()).isEqualTo(ACCESS_TOKEN);
    }

    @Test
    @DisplayName("사용자의 정보를 조회한다.")
    void getUserInfo() {
        long kakaoId = 41234L;
        String email = "email@email.com";
        String nickname = "tis";
        String profileImage = "profile_image.jpg";
        String thumbnail_image = "thumbnail_image.png";

        HashMap<String, String> properties = new HashMap<>();
        properties.put("nickname", nickname);
        properties.put("profile_image", profileImage);
        properties.put("thumbnail_image", thumbnail_image);

        HashMap<String, Object> kakaoAccount = new HashMap<>();
        kakaoAccount.put("email", email);

        when(authorizationClient.getUserInfo(
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            "Bearer" + " " + KAKAO_ACCESS_TOKEN.getAccessToken()
        )).thenReturn(ResponseEntity.ok(new KakaoUserInfo(kakaoId, properties, kakaoAccount)));

        KakaoMember member = kakaoAuthenticationService.getUserInfo(KAKAO_ACCESS_TOKEN);
        assertAll(
            () -> assertThat(member.getKakaoId()).isEqualTo(kakaoId),
            () -> assertThat(member.getEmail()).isEqualTo(email),
            () -> assertThat(member.getNickname()).isEqualTo(nickname),
            () -> assertThat(member.getProfileImageUrl()).isEqualTo(profileImage),
            () -> assertThat(member.getThumbnailImageUrl()).isEqualTo(thumbnail_image)
        );
    }
}

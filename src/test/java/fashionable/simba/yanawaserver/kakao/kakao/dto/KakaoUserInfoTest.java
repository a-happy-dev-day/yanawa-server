package fashionable.simba.yanawaserver.kakao.kakao.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KakaoUserInfoTest {
    Map<String, String> properties;
    Map<String, Object> kakaoAccount;

    @BeforeEach
    void setUp() {
        properties = new HashMap<>();
        kakaoAccount = new HashMap<>();
    }

    @Test
    @DisplayName("사용자 정보를 조회한다.")
    void userInfo_create() {
        assertDoesNotThrow(
            () -> new KakaoUserInfo(321L, properties, kakaoAccount)
        );
    }

    @Test
    @DisplayName("이메일 정보가 없다면 none이 된다.")
    void userInfo_IfNotExistEmail() {
        String none = "none";
        KakaoUserInfo userInfo = new KakaoUserInfo(321L, properties, kakaoAccount);

        assertThat(none).isEqualTo(userInfo.getEmail());
    }

    @Test
    @DisplayName("사용자의 프로퍼티 정보를 가져온다.")
    void userInfo_getProperties() {
        String email = "email";
        String userEmail = "email@email.com";

        properties.put(email, userEmail);
        KakaoUserInfo userInfo = new KakaoUserInfo(321L, properties, kakaoAccount);

        assertThat(userEmail).isEqualTo(userInfo.getProperties().get(email));
    }

    @Test
    @DisplayName("사용자의 계정 정보를 조회한다.")
    void userInfo_getKakaoAccount() {
        String nickname = "nickname";
        String username = "this-is-spear";

        kakaoAccount.put(nickname, username);
        KakaoUserInfo userInfo = new KakaoUserInfo(321L, properties, kakaoAccount);

        assertThat(username).isEqualTo(userInfo.getKakaoAccount().get(nickname));
    }
}

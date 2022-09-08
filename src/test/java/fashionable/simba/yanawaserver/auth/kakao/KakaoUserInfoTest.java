package fashionable.simba.yanawaserver.auth.kakao;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KakaoUserInfoTest {
    @Test
    @DisplayName("사용자 정보를 조회한다.")
    void userInfo_create() {

        Map<String, String> properties = new HashMap<>();
        Map<String, Object> kakaoAccount = new HashMap<>();

        assertDoesNotThrow(
            () -> new KakaoUserInfo(321L, properties, kakaoAccount)
        );
    }

    @Test
    @DisplayName("동등성 테스트")
    void userInfo_equals() {
        EqualsVerifier.forClass(KakaoUserInfo.class);
    }
}

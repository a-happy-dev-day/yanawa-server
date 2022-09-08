package fashionable.simba.yanawaserver.auth.kakao;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

class KakaoAccessTokenTest {

    @Test
    @DisplayName("액세스 토큰을 생성한다.")
    void accessToken_create() {
        Assertions.assertDoesNotThrow(
            () -> new KakaoAccessToken("Bearer", "accessToken", new Date(), "refreshToken", new Date())
        );
    }

    @Test
    @DisplayName("동등성 테스트")
    void accessToken_equals() {
        EqualsVerifier.forClass(KakaoAccessToken.class);
    }
}

package fashionable.simba.yanawaserver.auth.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KakaoAccessTokenTest {

    @Test
    @DisplayName("액세스 토큰을 생성한다.")
    void accessToken_create() {
        assertDoesNotThrow(
            () -> new KakaoAccessToken("Bearer", "accessToken", "refreshToken")
        );

    }

    @Test
    @DisplayName("동등성 테스트")
    void accessToken_equals() {
        EqualsVerifier.forClass(KakaoAccessToken.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}

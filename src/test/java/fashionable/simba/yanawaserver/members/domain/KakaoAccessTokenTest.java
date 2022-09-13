package fashionable.simba.yanawaserver.members.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KakaoAccessTokenTest {

    @Test
    @DisplayName("액세스 토큰을 생성한다.")
    void accessToken_create() {
        Date expiresIn = new Date();
        Date refreshTokenExpiresIn = new Date();
        KakaoAccessToken accessToken = assertDoesNotThrow(
            () -> new KakaoAccessToken("Bearer", "accessToken", expiresIn, "refreshToken", refreshTokenExpiresIn)
        );

        assertThat(accessToken.getExpiresIn()).isEqualTo(expiresIn);
        assertThat(accessToken.getRefreshTokenExpiresIn()).isEqualTo(refreshTokenExpiresIn);
    }

    @Test
    @DisplayName("동등성 테스트")
    void accessToken_equals() {
        EqualsVerifier.forClass(KakaoAccessToken.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}

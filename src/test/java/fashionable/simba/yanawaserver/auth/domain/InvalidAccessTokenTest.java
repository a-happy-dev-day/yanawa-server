package fashionable.simba.yanawaserver.auth.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InvalidAccessTokenTest {
    private static final String ACCESS_TOKEN = "access token";
    private static final Date DATE = new Date();

    @Test
    @DisplayName("유효하지 않은 액세스 코드를 생성한다.")
    void invalidAccessToken_create() {
        assertDoesNotThrow(
            () -> new InvalidAccessToken(ACCESS_TOKEN, DATE)
        );
    }

    @Test
    @DisplayName("유효하지 않은 액세스 코드의 날짜를 확인한다.")
    void invalidAccessToken_getDate() {
        InvalidAccessToken token = new InvalidAccessToken("access token", DATE);
        Assertions.assertThat(token.getDate()).isEqualTo(DATE);
    }
}
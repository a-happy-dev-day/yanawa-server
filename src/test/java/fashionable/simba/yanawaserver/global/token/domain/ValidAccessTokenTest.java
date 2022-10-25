package fashionable.simba.yanawaserver.global.token.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidAccessTokenTest {
    private static final Long USER_ID = 1L;
    private static final String ACCESS_TOKEN = "access token";

    @Test
    @DisplayName("유효하지 않은 액세스 코드를 생성한다.")
    void invalidAccessToken_create() {
        assertDoesNotThrow(
            () -> new ValidAccessToken(USER_ID, ACCESS_TOKEN)
        );
    }

    @Test
    @DisplayName("유효하지 않은 액세스 코드의 날짜를 확인한다.")
    void invalidAccessToken_getDate() {
        ValidAccessToken token = new ValidAccessToken(USER_ID, ACCESS_TOKEN);
        Assertions.assertThat(token.getUserId()).isEqualTo(USER_ID);
        Assertions.assertThat(token.getAccessToken()).isEqualTo(ACCESS_TOKEN);
    }
}

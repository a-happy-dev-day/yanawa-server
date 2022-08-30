package fashionable.simba.yanawaserver.auth.token;

import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JwtTokenProviderTest {
    JwtTokenProvider provider = new JwtTokenProvider("yanawa-secret-key", 1000);

    @Test
    @Order(1)
    @DisplayName("토큰을 생성한다.")
    void createToken() {
        assertDoesNotThrow(
            () -> provider.createToken("user@email.com", List.of(RoleType.ROLE_ADMIN.name()))
        );
    }

    @Test
    @Order(2)
    @DisplayName("토큰의 유효시간이 만료됐는지 확인한다.")
    void validToken() {
        // given
        String token = provider.createToken("user@email.com", List.of(RoleType.ROLE_ADMIN.name()));

        // then
        boolean actual = provider.validateToken(token);
        assertThat(actual).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("토큰의 유효시간이 만료되면 false를 리턴한다.")
    void validToken_expiration() throws InterruptedException {
        // given
        String token = provider.createToken("user@email.com", List.of(RoleType.ROLE_ADMIN.name()));

        // when
        Thread.sleep(1000);

        // then
        boolean actual = provider.validateToken(token);
        assertThat(actual).isFalse();
    }
}

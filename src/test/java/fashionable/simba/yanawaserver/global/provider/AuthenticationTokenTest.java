package fashionable.simba.yanawaserver.global.provider;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AuthenticationTokenTest {

    @Test
    @DisplayName("토큰 생성")
    void authenticationToken_create() {
        assertDoesNotThrow(
            () -> new AuthenticationToken("12345")
        );
    }

    @Test
    @DisplayName("동등성 비교")
    void authenticationToken_equals() {
        EqualsVerifier.forClass(AuthenticationToken.class)
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
    }
}

package fashionable.simba.yanawaserver.auth.filter;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AccessCodeTest {

    @Test
    @DisplayName("액세스 코드를 생성한다.")
    void accessCode_create() {
        String code = "access-code";
        AccessCode accessCode = assertDoesNotThrow(
            () -> new AccessCode(code)
        );

        assertThat(accessCode).isEqualTo(new AccessCode(code));
    }

    @Test
    @DisplayName("동등성 테스트")
    void accessCode_equals() {
        EqualsVerifier.forClass(AccessCode.class)
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
    }
}

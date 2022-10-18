package fashionable.simba.yanawaserver.members.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidLevelException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MemberLevelTest {

    @Test
    @DisplayName("사용자의 레벨을 작성한다.")
    void createLevel() {
        assertDoesNotThrow(
            () -> new MemberLevel(BigDecimal.valueOf(4))
        );
    }

    @ParameterizedTest
    @DisplayName("레벨이 0 이상 6이하가 아니면 InvalidLevelException 예외가 발생한다.")
    @ValueSource(longs = {-1, 10})
    void createLevel_invalidSize(long value) {
        BigDecimal invalidLevel = BigDecimal.valueOf(value);

        assertThatThrownBy(
            () -> new MemberLevel(invalidLevel)
        ).isInstanceOf(InvalidLevelException.class);
    }

    @Test
    @DisplayName("사용자의 레벨이 0.5 단위가 아니면 InvalidLevelException 예외가 발생한다.")
    void createLevel_invalidPoint() {
        BigDecimal invalidLevel = BigDecimal.valueOf(0.7);

        assertThatThrownBy(
            () -> new MemberLevel(invalidLevel)
        ).isInstanceOf(InvalidLevelException.class);
    }
}

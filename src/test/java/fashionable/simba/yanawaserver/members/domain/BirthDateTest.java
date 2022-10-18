package fashionable.simba.yanawaserver.members.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidBirthDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BirthDateTest {

    @Test
    @DisplayName("생일을 등록한다.")
    void createBirthDate() {
        MemberBirthDate birthDate = assertDoesNotThrow(
            () -> new MemberBirthDate(LocalDate.of(1996, 9, 1))
        );
        assertThat(birthDate.getBirth()).isEqualTo(LocalDate.of(1996, 9, 1));
    }

    @Test
    @DisplayName("생일은 현재 시간을 넘길 수 없다.")
    void createBirthDate_isBeforeNow() {
        LocalDate invalidDate = LocalDate.now().plusDays(2L);
        
        assertThatThrownBy(
            () -> new MemberBirthDate(invalidDate)
        ).isInstanceOf(InvalidBirthDateException.class);
    }
}

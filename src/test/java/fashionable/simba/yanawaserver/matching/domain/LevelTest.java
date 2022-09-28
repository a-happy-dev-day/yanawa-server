package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.LevelSettingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LevelTest {
    @Test
    @DisplayName("레벨이 0~6사이의 0.5단위로 설정되지 않을경우, LevelSettingException 발생한다.")
    void 레벨_생성_테스트() {
        assertThrows(LevelSettingException.class, () -> new Level(2.3));
    }

    @Test
    @DisplayName("레벨을 생성한다.")
    void create_level() {
        assertAll(
            () -> assertDoesNotThrow(() -> new Level()),
            () -> assertDoesNotThrow(() -> new Level(2.5))
        );
    }
}

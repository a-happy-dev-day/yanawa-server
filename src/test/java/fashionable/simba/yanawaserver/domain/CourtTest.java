package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.InvaildCourtNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CourtTest {
    @Test
    @DisplayName("코트장 생성 테스트")
    void 코트장_생성_테스트() {
        //given
        Court seoulTennisCourt = new Court(UUID.randomUUID(),"서울테니스장","서울");
        //when
        //then
        Assertions.assertEquals("서울테니스장", seoulTennisCourt.getName());
    }

    @Test
    @DisplayName("코트장 이름이 요구사항과 맞지 않을경우, InvaildCourtNameException이 발생한다.")
    void 코트장이름_테스트() {
        Assertions.assertThrows(InvaildCourtNameException.class, () -> {
            Court court = new Court(
                    UUID.randomUUID(),
                    null,
                    "서울"
            );
        });
    }


}

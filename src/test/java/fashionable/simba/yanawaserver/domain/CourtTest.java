package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.InvaildCourtLocation;
import fashionable.simba.yanawaserver.error.InvaildCourtNameException;
import fashionable.simba.yanawaserver.error.NoCourtDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CourtTest {
    @Test
    @DisplayName("코트장 생성 테스트")
    void 코트장_생성_테스트() {
        //given
        UUID id = UUID.randomUUID();
        String name = "서울테니스장";
        String location = "서울";
        //
        Court seoulTennisCourt = new Court.CourtBuilder(id)
                .setName(name)
                .setLocation(location).build();
        //when
        //then
        Assertions.assertEquals(id, seoulTennisCourt.getId());
        Assertions.assertEquals(name, seoulTennisCourt.getName());
        Assertions.assertEquals(location, seoulTennisCourt.getLocation());
    }

    @Test
    @DisplayName("코트장 이름이 요구사항과 맞지 않을경우, InvaildCourtNameException이 발생한다.")
    void 코트장이름_테스트() {
        Assertions.assertThrows(InvaildCourtNameException.class, () -> {
            Court court = new Court.CourtBuilder(UUID.randomUUID())
                    .setName(" ")
                    .setLocation("서울")
                    .build();
        });

        Assertions.assertThrows(InvaildCourtNameException.class, () -> {
            Court court2 = new Court.CourtBuilder(UUID.randomUUID())
                    .setName("")
                    .setLocation("서울")
                    .build();
        });

        Assertions.assertThrows(InvaildCourtNameException.class, () -> {
            Court court3 = new Court.CourtBuilder(UUID.randomUUID())
                    .setName("12345672132131231231289123345")
                    .setLocation("서울")
                    .build();
        });

    }

    @Test
    @DisplayName("코트장 지역이 요구사항과 맞지 않을 경우, InvaildCourtLocation이 발생한다.")
    void 코트장지역_테스트() {
        Assertions.assertThrows(InvaildCourtLocation.class, () -> {
            Court court = new Court.CourtBuilder(UUID.randomUUID())
                    .setName("부산테니스장")
                    .setLocation(null)
                    .build();
        });
        Assertions.assertThrows(InvaildCourtLocation.class, () -> {
            Court court = new Court.CourtBuilder(UUID.randomUUID())
                    .setName("부산테니스장")
                    .setLocation(" ")
                    .build();
        });
    }

    @Test
    @DisplayName("코트장 ID가 적절하지 않은경우, NoCourtDataException이 발생한다.")
    void 코트장ID_테스트() {
        //
        Assertions.assertThrows(NoCourtDataException.class, () -> {
            Court court = new Court.CourtBuilder(null)
                    .setName("부산테니스장")
                    .setLocation("부산")
                    .build();
        });
    }
}

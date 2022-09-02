package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.error.NoMatchingDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParticipationTest {
    @Test
    @DisplayName("참가 생성 테스트")
    void 매칭신청_생성_Test() {
        Participation participation = new Participation.Builder()
                .id(1L)
                .userId(1L)
                .matchingId(1L)
                .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                .status(ParticipationStatusType.WAITING)
                .build();
        //
        assertAll(
                () -> assertThat(participation.getId()).isEqualTo(1L),
                () -> assertThat(participation.getUserId()).isEqualTo(1L),
                () -> assertThat(participation.getMatchingId()).isEqualTo(1L),
                () -> assertThat(participation.getRequestDateTime()).isEqualTo(LocalDateTime.of(2022, 8, 22, 19, 30)),
                () -> assertThat(participation.getStatus()).isEqualTo(ParticipationStatusType.WAITING)
        );
    }

    @Test
    @DisplayName("참가 상태를 변경한다.")
    void 참가상태_변경_테스트() {
        //given
        Participation participation = new Participation.Builder()
                .id(1L)
                .userId(1L)
                .matchingId(1L)
                .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                .status(ParticipationStatusType.WAITING)
                .build();
        //when
        participation.setStatus(ParticipationStatusType.ACCEPTED);
        //then
        assertThat(participation.getStatus()).isEqualTo(ParticipationStatusType.ACCEPTED);
    }

    @Test
    @DisplayName("매칭 정보가 입력되지 않으면 NoMatchingExceoption이 발생한다.")
    void 매칭_아이디_실패_테스트() {
        assertThrows(NoMatchingDataException.class, () -> {
            new Participation.Builder()
                    .id(1L)
                    .userId(1L)
                    .matchingId(null)
                    .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                    .status(ParticipationStatusType.WAITING)
                    .build();
        });
    }
}

package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.error.NoMatchingDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParticipationTest {
    @Test
    @DisplayName("참가 생성 테스트")
    void 매칭신청_생성_Test() {
        Participation participation = new Participation(
                1L,
                1L,
                1L,
                LocalDateTime.of(2022, 9, 1, 18, 0),
                ParticipationStatusType.WAITING
        );
        //
        assertAll(
                () -> assertThat(participation.getId()).isEqualTo(1L),
                () -> assertThat(participation.getUserId()).isEqualTo(1L),
                () -> assertThat(participation.getMatchingId()).isEqualTo(1L),
                () -> assertThat(participation.getRequestDateTime()).isEqualTo(LocalDateTime.of(2022, 9, 1, 18, 0)),
                () -> assertThat(participation.getStatus()).isEqualTo(ParticipationStatusType.WAITING)
        );
    }

    @Test
    @DisplayName("참가 상태 ACCEPTED로 변경한다.")
    void 참가상태_변경_테스트() {
        //given
        Participation participation = new Participation(
                1L,
                1L,
                1L,
                LocalDateTime.of(2022, 9, 1, 18, 0),
                ParticipationStatusType.WAITING
        );
        //when
        participation.changeAcceptedParticipation();
        //then
        assertThat(participation.getStatus()).isEqualTo(ParticipationStatusType.ACCEPTED);
    }

    @Test
    @DisplayName("매칭 정보가 입력되지 않으면 NoMatchingExceoption이 발생한다.")
    void 매칭_아이디_실패_테스트() {
        assertThrows(NoMatchingDataException.class, () -> {
            new Participation(
                    1L,
                    1L,
                    null,
                    LocalDateTime.of(2022, 9, 1, 18, 0),
                    ParticipationStatusType.WAITING
            );
        });
    }
}

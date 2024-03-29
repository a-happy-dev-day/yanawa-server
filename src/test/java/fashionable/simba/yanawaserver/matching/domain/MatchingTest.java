package fashionable.simba.yanawaserver.matching.domain;


import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.error.MatchingTimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchingTest {

    @Test
    @DisplayName("매칭 도메인 테스트.")
    void 매칭_도메인_테스트() {
        Matching matching = assertDoesNotThrow(() ->
            getMatching(MatchingStatusType.WAITING)
        );

        assertAll(
            () -> assertThat(matching.getId()).isEqualTo(1L),
            () -> assertThat(matching.getCourtId()).isEqualTo(1L),
            () -> assertThat(matching.getHostId()).isEqualTo(1L),
            () -> assertThat(matching.getDate()).isEqualTo(LocalDate.of(2022, 7, 29)),
            () -> assertThat(matching.getStartTime()).isEqualTo(LocalTime.of(19, 0)),
            () -> assertThat(matching.getEndTime()).isEqualTo(LocalTime.of(21, 0)),
            () -> assertThat(matching.getStatus()).isEqualTo(MatchingStatusType.WAITING)
        );
    }

    @Test
    @DisplayName("매칭 상태 진행중으로 바꾸기")
    void 매칭상태_진행중_변경_테스트() {
        //given
        Matching matching = getMatching(MatchingStatusType.WAITING);
        //when
        assertAll(
            () -> assertDoesNotThrow(matching::changeOngoing),
            () -> assertThat(matching.getStatus()).isEqualTo(MatchingStatusType.ONGOING)
        );
        //then

    }

    @ParameterizedTest
    @EnumSource(value = MatchingStatusType.class, names = {"WAITING"}, mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("매칭 상태를 진행중으로 변경할때, 매칭상태가 WAITING이 아니라면 IllegalArgumentException가 발생한다.")
    void changeOnGoing_throwsException(MatchingStatusType statusType) {
        Matching matching = getMatching(statusType);
        assertThrows(IllegalArgumentException.class, matching::changeOngoing);
    }

    @Test
    @DisplayName("매칭 상태 완료로 바꾸기")
    void 매칭상태_완료_변경_테스트() {
        //given
        Matching matching = getMatching(MatchingStatusType.ONGOING);
        //when
        matching.changeFinished();
        //then
        assertThat(matching.getStatus()).isEqualTo(MatchingStatusType.FINISHED);
    }

    @ParameterizedTest
    @EnumSource(value = MatchingStatusType.class, names = {"ONGOING"}, mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("매칭 상태를 종료로 변경할때, 매칭상태가 ONGOING이 아니라면 IllegalArgumentException가 발생한다.")
    void changeFinished_throwException(MatchingStatusType statusType) {
        Matching matching = getMatching(statusType);
        assertThrows(IllegalArgumentException.class, matching::changeFinished);
    }

    @Test
    @DisplayName("시작 시간은 종료 시간보다 늦을 경우, MatchingTimeException이 발생한다.")
    void timeTest() {
        LocalDate date = LocalDate.of(2022, 7, 29);
        LocalTime startTime = LocalTime.of(21, 0);
        LocalTime endTime = LocalTime.of(19, 0);
        assertAll(
            () -> assertThrows(MatchingTimeException.class, () -> {
                new Matching(
                    1L,
                    1L,
                    date,
                    startTime,
                    endTime,
                    MatchingStatusType.WAITING
                );
            }),
            () -> assertThrows(MatchingTimeException.class, () -> {
                new Matching(
                    1L,
                    1L,
                    1L,
                    date,
                    startTime,
                    endTime,
                    MatchingStatusType.WAITING
                );
            })
        );
    }

    private static Matching getMatching(MatchingStatusType statusType) {
        return new Matching(
            1L,
            1L,
            1L,
            LocalDate.of(2022, 7, 29),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            statusType
        );
    }
}

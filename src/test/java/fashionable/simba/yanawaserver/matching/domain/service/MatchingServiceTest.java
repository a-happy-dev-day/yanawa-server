package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.repository.MemoryCourtRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MatchingServiceTest {
    MemoryMatchingRepository matchingRepository = new MemoryMatchingRepository();
    MemoryParticipationRepository participationRepository = new MemoryParticipationRepository();
    MemoryRecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    MatchingService matchingService = new MatchingService(matchingRepository, recruitmentRepository);
    MemoryCourtRepository fakeCourtRepository = new MemoryCourtRepository();
    static Long 서울_테니스장;

    @BeforeEach
    public void setUp() {
        matchingRepository.clear();
        participationRepository.clear();
        fakeCourtRepository.clear();
        서울_테니스장 = fakeCourtRepository.save("서울 테니스장");
        recruitmentRepository.clear();
    }

    @Test
    @DisplayName("매칭을 생성한다.")
    void create_matching_test() {
        //given
        Matching matching = getMatching(MatchingStatusType.WAITING);
        //
        Matching savedMatching = matchingService.createMatching(matching);
        //
        assertThat(matchingRepository.findMatchingById(savedMatching.getId()).orElseThrow()).isEqualTo(savedMatching);
    }

    @Test
    @DisplayName("모집이 완료된 매칭(CLOSING)을 진행(ONGOING)한다")
    void start_matching_test() {
        //given
        Matching matching = getMatching(MatchingStatusType.WAITING);
        Matching savedMatching = matchingService.createMatching(matching);
        Recruitment recruitment = getRecruitment(savedMatching, RecruitmentStatusType.CLOSED);
        recruitmentRepository.save(recruitment);
        //when
        matchingService.startMatching(savedMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(savedMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    @ParameterizedTest
    @EnumSource(value = RecruitmentStatusType.class, names = {"CLOSED"}, mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("모집이 CLOSED상태가 아니라면 매칭을 ONGOING으로 할때 IllegalArgumentException 발생한다.")
    void start_matching_throw_exception(RecruitmentStatusType statusType) {
        Matching matching = getMatching(MatchingStatusType.WAITING);
        Matching savedMatching = matchingService.createMatching(matching);
        Recruitment recruitment = getRecruitment(savedMatching, statusType);
        recruitmentRepository.save(recruitment);
        Long savedMatchingId = savedMatching.getId();

        assertThrows(IllegalArgumentException.class, () -> {
            matchingService.startMatching(savedMatchingId);
        });
    }

    @Test
    @DisplayName("매칭을 시작할때 모집정보가 없다면 IllegalArgumentException 발생한다.")
    void start_matching_throw_IllegalArgumentException() {
        Matching matching = getMatching(MatchingStatusType.WAITING);
        Matching savedMatching = matchingRepository.save(matching);
        Long savedMatchingId = savedMatching.getId();

        assertThrows(IllegalArgumentException.class, () -> {
            matchingService.startMatching(savedMatchingId);
        });
    }

    @Test
    @DisplayName("진행중인 매칭(ONGOING)을 종료(FINISHED)한다.")
    void end_matching_test() {
        //given
        Matching matching = getMatching(MatchingStatusType.ONGOING,
            LocalDate.of(2022, 9, 1),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0));
        Matching savedMatching = matchingService.createMatching(matching);
        //when
        matchingService.endMatching(savedMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(savedMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.FINISHED);
    }

    @ParameterizedTest
    @DisplayName("매칭을 종료(FINISHED)하려 할때, 매칭이 진행(ONGOING)이 아니라면 IllegalArgumentException이 발생한다.")
    @EnumSource(value = MatchingStatusType.class, names = {"ONGOING"}, mode = EnumSource.Mode.EXCLUDE)
    void end_matching_throw_exception(MatchingStatusType statusType) {
        //given
        Matching matching = getMatching(statusType);
        Matching savedMatching = matchingService.createMatching(matching);
        //then
        Long savedMatchingId = savedMatching.getId();

        assertThrows(IllegalArgumentException.class, () -> {
            matchingService.endMatching(savedMatchingId);
        });
    }

    @Test
    @DisplayName("매칭 종료시간이 지나지않으면 매칭을 종료시킬 수 없다.")
    void end_matching_time_check_test() {
        Matching matching = getMatching(MatchingStatusType.ONGOING
            , LocalDate.now(), LocalTime.now().minusHours(1), LocalTime.now().plusHours(1));
        Matching savedMatching = matchingService.createMatching(matching);
        Long savedMatchingId = savedMatching.getId();

        assertThrows(IllegalArgumentException.class, () -> {
            matchingService.endMatching(savedMatchingId);
        });
    }

    private static Matching getMatching(MatchingStatusType statusType) {
        return new Matching(
            서울_테니스장,
            1L,
            LocalDate.of(2022, 9, 1),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            statusType
        );
    }

    private static Matching getMatching(MatchingStatusType statusType, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return new Matching(
            서울_테니스장,
            1L,
            date,
            startTime,
            endTime,
            statusType
        );
    }

    private static Recruitment getRecruitment(Matching savedMatching, RecruitmentStatusType statusType) {
        return new Recruitment(
            savedMatching.getId(),
            new Level(5.0),
            new Level(1.0),
            AgeGroupType.TWENTIES,
            GenderType.NONE,
            PreferenceType.RALLY,
            4,
            2.0,
            AnnualType.FIVE_YEARS_LESS,
            "4명이서 랠리해요~",
            statusType
        );
    }
}

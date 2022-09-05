package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;
import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class RecruitmentServiceTest {
    MemoryMatchingRepository matchingRepository;
    MemoryParticipationRepository participationRepository;
    MatchingService matchingService;
    RecruitmentService recruitmentService;


    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
        participationRepository = new MemoryParticipationRepository();
        matchingService = new MatchingService(matchingRepository);
    }

    @Test
    @DisplayName("모집을 생성한다.")
    void create_recuitment() {
        MatchingRequsest requsest = new MatchingRequsest.Builder()
                .courtId(1L)
                .hostId(1L)
                .date(LocalDate.of(2022,9,3))
                .startTime(LocalTime.of(18,0,0))
                .endTime(LocalTime.of(20,0,0))
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(2.0))
                .ageOfRecruitment(AgeGroupType.ETC)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.MATCHING)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.NONE)
                .build();
    }

    @Test
    @DisplayName("진행자가 모집을 완료하면 모집을 완료한다.")
    void close_matching_by_host() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("인원이 차면 모집을 완료한다.")
    void full_member_matching_recruitment() {
        //given
        //when
        //then
    }
}

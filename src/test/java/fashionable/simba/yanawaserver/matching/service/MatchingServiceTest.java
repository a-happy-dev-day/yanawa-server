package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static fashionable.simba.yanawaserver.fixture.MatchingFixture.진행중인매칭;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingServiceTest {
    MemoryMatchingRepository matchingRepository;
    MemoryParticipationRepository participationRepository;
    MatchingService matchingService;

    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
        participationRepository = new MemoryParticipationRepository();
        matchingService = new MatchingService(matchingRepository, participationRepository);
    }

    @Test
    @DisplayName("매칭을 생성한다.")
    void create_matching_test() {
        //given
        Matching matching = new Matching.Builder()
                .courtId(1L)
                .hostId(1L)
                .date(LocalDate.of(2022,9,3))
                .startTime(LocalTime.of(18,0,0))
                .endTime(LocalTime.of(20,0,0))
                .build();
        Recruitment recruitment = new Recruitment.Builder()
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(2.0))
                .ageOfRecruitment(AgeGroupType.ETC)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.MATCHING)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.NONE)
                .build();
        //when
        matchingService.createMatching(matching, recruitment);
        assertThat(recruitmentRepository.findRecuitmentById(recruitment).getStatus()).isEqulto(RecruitmentStatusType.OPENING);
    }





    @Test
    @DisplayName("모집이 완료된 매칭(CLOSING)이 매칭 시작 시간이 되면 매칭을 진행한다")
    void start_matching_time() {
//        given
//        matchingRepository.save(모집완료_매칭);
//        //when
//        matchingService.startMatching(모집완료_매칭.getId());
//        //then
//        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    @Test
    @DisplayName("진행중인 매칭(ONGOING)이 종료 시간이 되면 매칭을 종료한다.")
    void end_matching_time() {
//        //given
//        matchingRepository.save(모집완료_매칭);
//        //when
//        matchingService.endMatching(모집완료_매칭.getId());
//        //then
//        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }


}

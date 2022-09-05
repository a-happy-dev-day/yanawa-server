package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecruitmentTest {
    MemoryMatchingRepository matchingRepository;
    MemoryParticipationRepository participationRepository;
    MatchingService matchingService;
    RecruitmentService recruitmentService;

    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
        participationRepository = new MemoryParticipationRepository();
        matchingService = new MatchingService(matchingRepository, participationRepository);
        recruitmentService = new RecruitmentService();
    }

    @Test
    @DisplayName("진행자가 모집을 완료하면 모집을 완료한다.")
    void close_matching_by_host() {
        //given
        matchingRepository.save(모집중인_매칭);
        participationRepository.save(fixtureParticipation1);
        //when
        matchingService.closeMatchingStatus(모집중인_매칭.getId());
        //then
        assertThat(matchingRepository.findMatchingById(모집중인_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.CLOSING);
    }

    @Test
    @DisplayName("인원이 차면 모집을 완료한다.")
    void full_member_matching_recruitment() {
        //given
        matchingRepository.save(모집중인_매칭);
        participationRepository.save(fixtureParticipation1);
        participationRepository.save(fixtureParticipation2);
        participationRepository.save(fixtureParticipation3);
        //when
        matchingService.finishMatching(모집중인_매칭.getId());
        //then
        assertThat(matchingRepository.findMatchingById(모집중인_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.FINISH);
    }
}

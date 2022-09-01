package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static fashionable.simba.yanawaserver.fixture.MatchingFixture.모집중인_매칭;
import static fashionable.simba.yanawaserver.fixture.MatchingFixture.모집완료_매칭;
import static fashionable.simba.yanawaserver.fixture.ParticipationFixture.*;
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
    @DisplayName("호스트가 모집을 완료하면, 매칭 모집상태를 완료(closing)로 변경한다.")
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
    @DisplayName("인원이 차면 매칭 모집을 완료(finish)로 변경한다.")
    void full_member_matching_recruitment() {
        //given
        matchingRepository.save(모집중인_매칭);
        participationRepository.save(fixtureParticipation1);
        participationRepository.save(fixtureParticipation2);
        participationRepository.save(fixtureParticipation3);
        //when
        matchingService.fullMemberMatching(모집중인_매칭.getId());
        //then
        assertThat(matchingRepository.findMatchingById(모집중인_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.FINISH);
    }

    @Test
    @DisplayName("시작 시간이 되면 매칭을 진행한다")
    void start_matching_time() {
        //given
        matchingRepository.save(모집완료_매칭);
        //when
        matchingService.startMatching(모집완료_매칭.getId());
        //then
        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    @Test
    @DisplayName("진행중인 매칭이 종료 시간이 되면 매칭을 종료한다.")
    void end_matching_time() {
        //given
        matchingRepository.save(모집완료_매칭);
        //when
        matchingService.endMatching(모집완료_매칭.getId());
        //then
        assertThat(matchingRepository.findMatchingById(모집완료_매칭.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }


}

package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static fashionable.simba.yanawaserver.fixture.MatchingFixture.FIXTURE_MATCHING_ID;
import static fashionable.simba.yanawaserver.fixture.MatchingFixture.fixtureMatching;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchingServiceTest {
    MemoryMatchingRepository matchingRepository = new MemoryMatchingRepository();
    MatchingService matchingService = new MatchingService(matchingRepository);
    MemoryParticipationRepository ParticipationRepository = new MemoryParticipationRepository();

    @BeforeEach
    public void setUp() {
        MemoryMatchingRepository repository = new MemoryMatchingRepository();
        MatchingService matchingService = new MatchingService(repository);
    }

    @Test
    @DisplayName("호스트가 모집을 완료하면, 매칭 모집상태를 완료(closing)로 변경한다.")
    void close_matching_by_host() {
        //given
        matchingRepository.save(fixtureMatching);
        //when
        matchingService.closeMatchingStatus(fixtureMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.CLOSING);
    }

    @Test
    @DisplayName("인원이 차면 매칭 모집을 완료(finish)로 변경한다.")
    void full_member_matching_recruitment() {
        //given
        matchingRepository.save(fixtureMatching);
        //when
        matchingService.finishMatchingStatus(fixtureMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.FINISH);
    }

    @Test
    @DisplayName("모집이 완료된 매칭이 매칭 시작 시간이 되면 매칭을 진행한다")
    void start_matching_time() {
        //given
        matchingRepository.save(fixtureMatching);
        //when
        matchingService.ongoingMatchingStatus(fixtureMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    @Test
    @DisplayName("진행중인 매칭이 종료 시간이 되면 매칭을 종료한다.")
    void end_matching_time() {
        //given
        matchingRepository.save(fixtureMatching);
        //when
        matchingService.closeMatchingStatus(fixtureMatching.getId());
        //then
        assertThat(matchingRepository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.ONGOING);
    }

    
}

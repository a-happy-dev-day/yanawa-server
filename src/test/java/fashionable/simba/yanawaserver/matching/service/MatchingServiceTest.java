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
    MemoryMatchingRepository repository = new MemoryMatchingRepository();
    MatchingService matchingService = new MatchingService(repository);
    MemoryParticipationRepository ParticipationRepository = new MemoryParticipationRepository();

    @BeforeEach
    public void setUp() {
        MemoryMatchingRepository repository = new MemoryMatchingRepository();
        MatchingService matchingService = new MatchingService(repository);
    }

    @Test
    @DisplayName("호스트가 모집을 완료하면, 매칭 모집상태를 완료(closing)로 변경한다.")
    void matching_finish_by_host() {
        //given
        repository.save(fixtureMatching);
        //when
        matchingService.changeStatus(FIXTURE_MATCHING_ID, MatchingStatusType.CLOSING);
        //then
        assertThat(repository.findMatchingById(fixtureMatching.getId()).orElseThrow().getStatus()).isEqualTo(MatchingStatusType.CLOSING);
    }



}

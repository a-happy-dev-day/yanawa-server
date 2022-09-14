package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.repository.MatchingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

class MatchingRepositoryTest {
    private static Logger logger = LoggerFactory.getLogger(MatchingRepositoryTest.class);

    MatchingRepository matchingRepository;

    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
    }

    @Test
    void saveTest() {
        Matching matching = new Matching.Builder()
                .courtId(1L)
                .hostId(1L)
                .date(LocalDate.of(2022, 9, 3))
                .startTime(LocalTime.of(18, 0, 0))
                .endTime(LocalTime.of(20, 0, 0))
                .build();
        Long id = matchingRepository.save(matching).getId();
        assertThat(matchingRepository.findMatchingById(id).orElseThrow().getId()).isEqualTo(id);
    }
}

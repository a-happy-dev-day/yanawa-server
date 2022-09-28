package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryMatchingRepository;
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
        Matching matching = new Matching(
            1L,
            1L,
            LocalDate.of(2022, 9, 3),
            LocalTime.of(18, 0, 0),
            LocalTime.of(20, 0, 0),
            MatchingStatusType.WAITING
        );
        Long id = matchingRepository.save(matching).getId();
        assertThat(matchingRepository.findById(id).orElseThrow().getId()).isEqualTo(id);
    }
}

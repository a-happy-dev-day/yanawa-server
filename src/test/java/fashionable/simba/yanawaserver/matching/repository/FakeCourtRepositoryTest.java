package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.CourtRepository;
import fashionable.simba.yanawaserver.matching.infra.MemoryCourtRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FakeCourtRepositoryTest {
    CourtRepository courtRepository = new MemoryCourtRepository();

    @Test
    void save_court() {
        Long courtId = courtRepository.save("서울 테니스장");
        assertThat(courtRepository.findCourtById(courtId).orElseThrow()).isEqualTo("서울 테니스장");
    }
}

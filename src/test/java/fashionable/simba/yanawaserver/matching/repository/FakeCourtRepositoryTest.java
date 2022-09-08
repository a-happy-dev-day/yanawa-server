package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
import org.junit.jupiter.api.Test;

public class FakeCourtRepositoryTest {
    CourtRepository courtRepository = new MemoryCourtRepository();

    @Test
    void save_court() {
        System.out.println(courtRepository.findCourtNameById(1L));
    }
}

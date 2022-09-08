package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface CourtRepository {
    Long save(String courtName);

    Optional<String> findCourtNameById(Long id);
}

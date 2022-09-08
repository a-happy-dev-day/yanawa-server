package fashionable.simba.yanawaserver.matching.domain.repository;

import java.util.Optional;

public interface CourtRepository {
    Long save(String courtName);

    void clear();

    Optional<String> findCourtNameById(Long id);
}

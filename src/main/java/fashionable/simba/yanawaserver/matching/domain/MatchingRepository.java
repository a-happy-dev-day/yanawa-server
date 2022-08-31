package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface MatchingRepository {
    void save(Matching matching);

    Optional<Matching> findMatchingById(Long matchingId);


}

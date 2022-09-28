package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface MatchingRepository {
    Matching save(Matching matching);

    Optional<Matching> findById(Long id);
}

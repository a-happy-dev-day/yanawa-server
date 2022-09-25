package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;

import java.util.Optional;

public interface MatchingRepository {
    Matching save(Matching matching);

    Optional<Matching> findById(Long id);
}

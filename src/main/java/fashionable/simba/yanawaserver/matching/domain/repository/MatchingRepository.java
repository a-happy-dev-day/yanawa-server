package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;
import fashionable.simba.yanawaserver.matching.domain.Matching;

import java.util.Optional;

public interface MatchingRepository {
    Matching save(Matching matching);

    void clear();

    Optional<Matching> findMatchingById(Long id);


}

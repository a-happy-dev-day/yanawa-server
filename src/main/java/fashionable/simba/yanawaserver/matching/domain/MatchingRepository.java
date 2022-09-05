package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;

import java.util.Optional;

public interface MatchingRepository {
    Matching save(Matching matching);

    Optional<Matching> findMatchingById(Long id);


}

package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryMatchingRepository implements MatchingRepository {
    public static final Map<Long, Matching> matchings = new HashMap<>();

    @Override
    public void save(Matching matching) {
        matchings.put(matching.getId(), matching);
    }

    @Override
    public Optional<Matching> findMatchingById(Long matchingId) {
        return Optional.ofNullable(matchings.get(matchingId));
    }

}

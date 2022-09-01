package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMatchingRepository implements MatchingRepository {
    private static final Map<Long, Matching> matchings = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    @Override
    public Matching save(Matching matching) {
        Long id = getId();
        Matching save = new Matching.Builder().id(id)
                .date(matching.getDate())
                .startTime(matching.getStartTime())
                .endTime(matching.getEndTime())
                .build();
        matchings.put(id, save);
        return save;
    }

    private synchronized Long getId() {
        return ++sequence;
    }

    @Override
    public Optional<Matching> findMatchingById(Long matchingId) {
        return Optional.ofNullable(matchings.get(matchingId));
    }

}

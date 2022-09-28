package fashionable.simba.yanawaserver.matching.fake;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.repository.MatchingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMatchingRepository implements MatchingRepository {
    private final Map<Long, Matching> matchings = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Matching save(Matching matching) {
        Long id = getId();
        Matching save = new Matching(
            id,
            matching.getHostId(),
            matching.getCourtId(),
            matching.getDate(),
            matching.getStartTime(),
            matching.getEndTime(),
            matching.getStatus()
        );
        matchings.put(id, save);
        return save;
    }

    private synchronized Long getId() {
        return ++sequence;
    }


    @Override
    public Optional<Matching> findById(Long id) {
        return Optional.ofNullable(matchings.get(id));
    }

}

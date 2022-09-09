package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCourtRepository implements CourtRepository {
    private final Map<Long, String> courts = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Long save(String courtName) {
        Long id = getId();
        courts.put(id, courtName);
        return id;
    }

    private synchronized Long getId() {
        return ++sequence;
    }

    @Override
    public void clear() {
        courts.clear();
    }

    @Override
    public Optional<String> findCourtById(Long id) {
        return Optional.ofNullable(courts.get(id));
    }

    @Override
    public boolean isCourtExist(Long id) {
        return courts.get(id) != null;
    }
}

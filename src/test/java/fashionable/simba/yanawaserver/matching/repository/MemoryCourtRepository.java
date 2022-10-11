package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.court.domain.Court;
import fashionable.simba.yanawaserver.matching.domain.CourtApi;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCourtRepository implements CourtApi {
    private final Map<Long, Court> courts = new HashMap<>();
    private Long sequence = 0L;

    public Long save(String courtName) {
        Long id = getId();
        courts.put(id, new Court(id, "어딘가", courtName, null));
        return id;
    }

    private synchronized Long getId() {
        return ++sequence;
    }

    public Optional<Court> findCourtById(Long id) {
        return Optional.ofNullable(courts.get(id));
    }

    @Override
    public boolean isCourtExist(Long id) {
        return courts.get(id) != null;
    }
}

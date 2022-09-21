package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryParticipationRepository implements ParticipationRepository {
    private static final Map<Long, Participation> participations = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Participation save(Participation participation) {
        Long id = getId();
        Participation save = new Participation(
            id,
            participation.getUserId(),
            participation.getMatchingId(),
            participation.getRequestDateTime(),
            participation.getStatus()
        );
        participations.put(id, save);
        return save;
    }

    private synchronized Long getId() {
        return ++sequence;
    }


    @Override
    public Optional<Participation> findParticipationById(Long id) {
        return Optional.ofNullable(participations.get(id));
    }

    @Override
    public void clear() {
        participations.clear();
    }

    @Override
    public Integer countParticipationsByMatchingId(Long matchingId) {
        return (int) participations.values().stream().filter(x -> x.getMatchingId().equals(matchingId)).count();
    }
}

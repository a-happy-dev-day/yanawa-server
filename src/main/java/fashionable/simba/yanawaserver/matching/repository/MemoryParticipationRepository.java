package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryParticipationRepository implements ParticipationRepository {
    public static Map<Long, Participation> participations = new HashMap<>();

    @Override
    public void save(Participation Participation) {
        participations.put(Participation.getId(), Participation);
    }

    @Override
    public Optional<Participation> findParticipationById(Long id) {
        return Optional.ofNullable(participations.get(id));
    }

    @Override
    public Integer countParticipationsByMatchingId(Long Matchingid) {
        return (int) participations.values().stream().filter(x -> x.getMatchingId().equals(Matchingid)).count();
    }
}

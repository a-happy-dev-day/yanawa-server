package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryParticipationRepository implements ParticipationRepository {
    public static Map<Long, Participation> applies = new HashMap<>();

    @Override
    public void save(Participation apply) {
        applies.put(apply.getId(), apply);
    }

    @Override
    public Optional<Participation> findParticipationById(Long id) {
        return Optional.ofNullable(applies.get(id));
    }

    @Override
    public Integer countAppliesdById(Long id) {
        return (int) applies.values().stream().filter(x -> x.getMatchingId().equals(id)).count();
    }
}

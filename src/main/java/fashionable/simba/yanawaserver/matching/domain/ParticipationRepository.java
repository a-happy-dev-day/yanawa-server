package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface ParticipationRepository {
    void save(Participation apply);

    Optional<Participation> findParticipationById(Long id);

    Integer countParticipationsById(Long id);
}

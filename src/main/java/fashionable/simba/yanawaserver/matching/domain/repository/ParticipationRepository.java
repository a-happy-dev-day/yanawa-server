package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Participation;

import java.util.Optional;

public interface ParticipationRepository {
    Participation save(Participation apply);

    Optional<Participation> findById(Long id);

    Integer countParticipationsByMatchingId(Long matchingId);
}

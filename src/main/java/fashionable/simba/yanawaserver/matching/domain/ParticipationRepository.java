package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface ParticipationRepository {
    Participation save(Participation apply);

    Optional<Participation> findById(Long id);

    Integer countParticipationsByMatchingId(Long matchingId);

    Optional<Participation> findByMatchingIdAndUserId(Long recruitmentId, Long participantId);
}

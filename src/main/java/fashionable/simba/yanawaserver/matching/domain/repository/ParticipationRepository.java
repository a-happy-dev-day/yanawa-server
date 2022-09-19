package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Participation;

import java.util.Optional;

public interface ParticipationRepository {
    Participation save(Participation apply);

    Optional<Participation> findParticipationById(Long id);

    Optional<Participation> findParticipationByUserIdAndRecruitmentId(Long userId, Long recruitmentId);

    void clear();

    Integer countParticipationsByMatchingId(Long matchingId);
}

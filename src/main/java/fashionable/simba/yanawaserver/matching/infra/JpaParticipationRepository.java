package fashionable.simba.yanawaserver.matching.infra;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaParticipationRepository extends JpaRepository<Participation, Long>, ParticipationRepository {
    @Override
    Participation save(Participation participation);

    @Override
    Optional<Participation> findById(Long aLong);

    @Override
    Integer countParticipationsByMatchingId(Long matchingId);

}

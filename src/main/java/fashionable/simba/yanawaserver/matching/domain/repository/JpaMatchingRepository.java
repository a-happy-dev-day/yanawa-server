package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMatchingRepository extends JpaRepository<Matching, Long>, MatchingRepository {
    @Override
    Matching save(Matching matching);

    @Override
    Optional<Matching> findById(Long aLong);


}

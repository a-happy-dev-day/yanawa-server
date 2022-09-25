package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMatchingRepository extends JpaRepository<Matching, Long>, MatchingRepository {
}

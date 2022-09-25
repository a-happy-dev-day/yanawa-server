package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentRepository {
}

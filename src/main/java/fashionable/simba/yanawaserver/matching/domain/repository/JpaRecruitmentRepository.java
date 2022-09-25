package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Primary
@Transactional
public interface JpaRecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentRepository {
}

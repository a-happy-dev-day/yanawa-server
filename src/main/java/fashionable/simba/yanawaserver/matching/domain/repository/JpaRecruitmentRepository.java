package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentRepository {
    @Override
    Recruitment save(Recruitment recruitment);

    @Override
    Optional<Recruitment> findById(Long aLong);

    @Override
    List<Recruitment> findAll();

    Optional<Recruitment> findByMatchingId(Long id);
}

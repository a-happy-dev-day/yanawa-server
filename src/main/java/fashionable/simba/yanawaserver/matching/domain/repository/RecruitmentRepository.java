package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);

    Optional<Recruitment> findById(Long id);

    Optional<Recruitment> findByMatchingId(Long id);

    List<Recruitment> findAll();
}

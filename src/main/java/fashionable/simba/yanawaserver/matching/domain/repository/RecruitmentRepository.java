package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);

    void clear();

    Optional<Recruitment> findRecruitmentById(Long id);

    List<Recruitment> findRecruitmentByMatchingId(Long matchingId);
}

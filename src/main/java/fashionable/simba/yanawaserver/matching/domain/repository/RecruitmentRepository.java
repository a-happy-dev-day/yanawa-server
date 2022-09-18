package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;

import java.util.Optional;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);

    void clear();

    Optional<Recruitment> findRecruitmentById(Long id);

    Optional<Recruitment> findRecruitmentByMatchingId(Long matchingId);
}

package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;

import java.util.Optional;

public interface RecruitmentRepository {
    Recruitment save(Recruitment recruitment);

    Optional<Recruitment> findRecruitmentById(Long id);
}

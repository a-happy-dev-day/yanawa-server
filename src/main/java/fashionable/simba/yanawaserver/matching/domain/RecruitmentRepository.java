package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;

import java.util.Optional;

public interface RecruitmentRepository {
    Recruitment save(MatchingRequsest request);

    Optional<Recruitment> findRecruitmentById(Long id);
}

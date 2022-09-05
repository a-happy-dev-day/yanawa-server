package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface RecruitmentRepository {
    void save(Recruitment recruitment);

    Optional<Recruitment> findRecruitmentById(Long id);
}

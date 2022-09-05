package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryRecruitmentRepository implements RecruitmentRepository {
    private static final Map<Long, Recruitment> recruitments = new HashMap<>();

    @Override
    public void save(Recruitment recruitment) {

    }

    @Override
    public Optional<Recruitment> findRecruitmentById(Long id) {
        return Optional.empty();
    }
}

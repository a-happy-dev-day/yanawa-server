package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryRecruitmentRepository implements RecruitmentRepository {
    private static final Map<Long, Recruitment> recruitments = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Recruitment save(Recruitment recruitment) {
        Long id = getId();
        Recruitment save = new Recruitment(
            id,
            recruitment.getMatchingId(),
            recruitment.getMaximumLevel(),
            recruitment.getMinimumLevel(),
            recruitment.getAgeOfRecruitment(),
            recruitment.getSexOfRecruitment(),
            recruitment.getPreferenceGame(),
            recruitment.getNumberOfRecruitment(),
            recruitment.getCostOfCourtPerPerson(),
            recruitment.getAnnual(),
            recruitment.getDetails(),
            recruitment.getStatus()
        );
        recruitments.put(id, save);
        return save;
    }

    @Override
    public void clear() {
        recruitments.clear();
    }

    private synchronized Long getId() {
        return ++sequence;
    }

    @Override
    public Optional<Recruitment> findRecruitmentById(Long id) {
        return Optional.ofNullable(recruitments.get(id));
    }

    public Optional<Recruitment> findRecruitmentByMatchingId(Long matchingId) {
        return recruitments.values().stream().filter(x -> x.getMatchingId().equals(matchingId)).findAny();
    }
}

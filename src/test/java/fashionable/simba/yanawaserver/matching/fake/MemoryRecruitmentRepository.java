package fashionable.simba.yanawaserver.matching.fake;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryRecruitmentRepository implements RecruitmentRepository {
    private final Map<Long, Recruitment> recruitments = new HashMap<>();
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

    private synchronized Long getId() {
        return ++sequence;
    }

    @Override
    public Optional<Recruitment> findById(Long id) {
        return Optional.ofNullable(recruitments.get(id));
    }

    @Override
    public Optional<Recruitment> findByMatchingId(Long id) {
        return recruitments.values().stream().filter(recruitment -> recruitment.getMatchingId().equals(id)).findAny();
    }

    @Override
    public List<Recruitment> findAll() {
        return new ArrayList<>(recruitments.values());
    }

    public Optional<Recruitment> findRecruitmentByMatchingId(Long matchingId) {
        return recruitments.values().stream().filter(x -> x.getMatchingId().equals(matchingId)).findAny();
    }
}

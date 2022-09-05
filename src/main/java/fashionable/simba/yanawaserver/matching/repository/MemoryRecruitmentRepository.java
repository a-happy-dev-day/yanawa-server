package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryRecruitmentRepository implements RecruitmentRepository {
    private static final Map<Long, Recruitment> recruitments = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Recruitment save(Recruitment recruitment) {
        Long id = getId();
        Recruitment save = new Recruitment.Builder()
                .id(id)
                .matchingId(recruitment.getMatchingId())
                .maximumLevel(recruitment.getMaximumLevel())
                .minimumLevel(recruitment.getMinimumLevel())
                .ageOfRecruitment(recruitment.getAgeOfRecruitment())
                .sexOfRecruitment(recruitment.getSexOfRecruitment())
                .preferenceGame(recruitment.getPreferenceGame())
                .numberOfRecruitment(recruitment.getNumberOfRecruitment())
                .costOfCourtPerPerson(recruitment.getCostOfCourtPerPerson())
                .annual(recruitment.getAnnual())
                .details(recruitment.getDetails())
                .status(recruitment.getStatus())
                .build();
        recruitments.put(id, save);
        return save;
    }

    private synchronized Long getId() {
        return ++sequence;
    }

    @Override
    public Optional<Recruitment> findRecruitmentById(Long id) {
        return Optional.ofNullable(recruitments.get(id));
    }
}

package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.application.MatchingRequsest;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryRecruitmentRepository implements RecruitmentRepository {
    private static final Map<Long, Recruitment> recruitments = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Recruitment save(MatchingRequsest request) {
        Long id = getId();
        Recruitment save = new Recruitment.Builder()
                .id(id)
                .maximumLevel(request.getMaximumLevel())
                .minimumLevel(request.getMinimumLevel())
                .ageOfRecruitment(request.getAgeOfRecruitment())
                .sexOfRecruitment(request.getSexOfRecruitment())
                .preferenceGame(request.getPreferenceGame())
                .numberOfRecruitment(request.getNumberOfRecruitment())
                .costOfCourtPerPerson(request.getCostOfCourtPerPerson())
                .annual(request.getAnnual())
                .details(request.getDetails())
                .status(request.getRecruitmentStatus())
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

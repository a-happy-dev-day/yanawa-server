package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {
    private final MatchingRepository matchingRepository;

    public MatchingService(MatchingRepository matchingRepository) {
        this.matchingRepository = matchingRepository;
    }


    public void changeStatus(Long id, MatchingStatusType status) {
        Matching matching = matchingRepository.findMatchingById(id);
        matching.setStatus(status);
        matchingRepository.save(matching);
    }


}

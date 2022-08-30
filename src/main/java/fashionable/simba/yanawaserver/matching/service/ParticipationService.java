package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final MatchingRepository matchingRepository;

    public ParticipationService(ParticipationRepository participationRepository, MatchingRepository matchingRepository) {
        this.participationRepository = participationRepository;
        this.matchingRepository = matchingRepository;
    }

    public void changeStatus(Long id, RequestStatusType status) {
        Participation participation = participationRepository.findParticipationById(id).orElseThrow();
        participation.setStatus(status);
        participationRepository.save(participation);
    }


    public boolean checkAvailableParticipation(Integer numberOfRecruitment, Integer numberOfApplies) {

        return numberOfRecruitment > numberOfApplies;
    }
}

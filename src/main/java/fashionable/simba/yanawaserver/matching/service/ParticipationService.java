package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;

public class ParticipationService {
    public ParticipationRepository matchingApplyRepository;

    public ParticipationService(ParticipationRepository matchingApplyRepository) {
        this.matchingApplyRepository = matchingApplyRepository;
    }

    public void changeStatus(Long id, RequestStatusType status) {
        Participation matchingApply = matchingApplyRepository.findParticipationById(id).orElseThrow();
        matchingApply.setStatus(status);
        matchingApplyRepository.save(matchingApply);
    }

    public boolean checkAvailableParticipation(Integer numberOfRecruitment, Integer numberOfApplies) {
        return numberOfRecruitment > numberOfApplies;
    }
}

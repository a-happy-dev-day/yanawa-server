package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.domain.MatchingApply;
import fashionable.simba.yanawaserver.matching.domain.MatchingApplyRepository;

public class MatchingApplyService {
    public MatchingApplyRepository matchingApplyRepository;

    public MatchingApplyService(MatchingApplyRepository matchingApplyRepository) {
        this.matchingApplyRepository = matchingApplyRepository;
    }

    public void changeStatus(Long id, RequestStatusType status) {
        MatchingApply matchingApply = matchingApplyRepository.findMatchingApplyById(id).orElseThrow();
        matchingApply.setStatus(status);
        matchingApplyRepository.save(matchingApply);
    }

    public boolean checkAvailableApply(Integer numberOfRecruitment, Integer numberOfApplies) {
        return numberOfRecruitment > numberOfApplies;
    }
}

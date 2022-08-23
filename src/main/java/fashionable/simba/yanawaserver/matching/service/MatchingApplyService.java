package fashionable.simba.yanawaserver.matching.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.MatchingApply;
import fashionable.simba.yanawaserver.matching.domain.MatchingApplyRepository;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;

public class MatchingApplyService {
    public MatchingApplyRepository matchingApplyRepository;
    public MatchingRepository matchingRepository;

    public MatchingApplyService(MatchingApplyRepository matchingApplyRepository) {
        this.matchingApplyRepository = matchingApplyRepository;
    }

    public void checkAvailableApply(MatchingApply apply) {
        Integer numberOfRecruitment = matchingRepository.findMatchingById(apply.getMatchingId()).getNumberOfRecruitment();
        Integer numberOfApplies = matchingApplyRepository.countAppliesdById(apply.getId());
        if (numberOfRecruitment == numberOfApplies + 1) {
            matchingRepository.findMatchingById(apply.getMatchingId()).setStatus(MatchingStatusType.FINISH);
            return;
        }
        matchingApplyRepository.save(apply);
    }

//    public void changeMatchingApply(Long applyId, RequestStatusType status) {
//        MatchingApply matchingApply = matchingApplyRepository.findMatchingApplyById(applyId);
//        matchingApply.setStatus(status);
//        matchingApplyRepository.save(matchingApply);
//    }

}

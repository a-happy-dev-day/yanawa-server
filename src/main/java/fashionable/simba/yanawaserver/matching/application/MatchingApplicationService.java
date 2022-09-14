package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;

public class MatchingApplicationService {
    private final MatchingService matchingService;
    private final RecruitmentService recruitmentService;
    private final CourtRepository courtRepository;

    public MatchingApplicationService(MatchingService matchingService, RecruitmentService recruitmentService, CourtRepository courtRepository) {
        this.matchingService = matchingService;
        this.recruitmentService = recruitmentService;
        this.courtRepository = courtRepository;
    }

    public MatchingResponse createMatchingAndRecruitment(MatchingRequsest requsest) {
        Matching matching = new Matching(
                requsest.getHostId(),
                requsest.getCourtId(),
                requsest.getDate(),
                requsest.getStartTime(),
                requsest.getEndTime(),
                MatchingStatusType.WAITING
        );
        boolean isCourtExist = courtRepository.isCourtExist(matching.getCourtId());
        if (!isCourtExist) {
            throw new NoCourtDataException("코트장 정보를 조회할 수 없습니다.");
        }
        Matching savedMatching = matchingService.createMatching(matching);

        Recruitment recruitment = new Recruitment(
                requsest.getCourtId(),
                requsest.getMaximumLevel(),
                requsest.getMinimumLevel(),
                requsest.getAgeOfRecruitment(),
                requsest.getSexOfRecruitment(),
                requsest.getPreferenceGame(),
                requsest.getNumberOfRecruitment(),
                requsest.getCostOfCourtPerPerson(),
                requsest.getAnnual(),
                requsest.getDetails(),
                RecruitmentStatusType.OPENING
        );
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);

        return new MatchingResponse(
                savedRecruitment.getId(),
                savedMatching.getId(),
                savedMatching.getCourtId(),
                savedMatching.getHostId(),
                savedMatching.getDate(),
                savedMatching.getStartTime(),
                savedMatching.getEndTime(),
                savedMatching.getStatus(),
                savedRecruitment.getMaximumLevel(),
                savedRecruitment.getMinimumLevel(),
                savedRecruitment.getAgeOfRecruitment(),
                savedRecruitment.getSexOfRecruitment(),
                savedRecruitment.getPreferenceGame(),
                savedRecruitment.getNumberOfRecruitment(),
                savedRecruitment.getCostOfCourtPerPerson(),
                savedRecruitment.getAnnual(),
                savedRecruitment.getDetails(),
                savedRecruitment.getStatus()
        );
    }
}

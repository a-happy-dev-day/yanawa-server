package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponse;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponses;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.CourtApi;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingApplicationService {
    private final MatchingService matchingService;
    private final RecruitmentService recruitmentService;
    private final CourtApi courtRepository;

    public MatchingApplicationService(MatchingService matchingService, RecruitmentService recruitmentService, CourtApi courtRepository) {
        this.matchingService = matchingService;
        this.recruitmentService = recruitmentService;
        this.courtRepository = courtRepository;
    }

    public RecruitmentResponse createMatchingAndRecruitment(RecruitmentRequsest requsest) {
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

        return new RecruitmentResponse(
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

    public RecruitmentResponses findAll() {
        List<RecruitmentResponse> responses = new ArrayList<>();
        List<Recruitment> recruitments = recruitmentService.findAllRecruitment();

        for (Recruitment recruitment : recruitments) {
            Matching matching = matchingService.findMatching(recruitment.getMatchingId());
            RecruitmentResponse response = new RecruitmentResponse(
                recruitment.getId(),
                recruitment.getMatchingId(),
                matching.getCourtId(),
                matching.getHostId(),
                matching.getDate(),
                matching.getStartTime(),
                matching.getEndTime(),
                matching.getStatus(),
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
            responses.add(response);
        }
        return new RecruitmentResponses(responses);
    }

    public RecruitmentResponse findOne(Long matchingId) {
        Recruitment recruitment = recruitmentService.findRecruitment(matchingId);
        Matching matching = matchingService.findMatching(recruitment.getMatchingId());

        return new RecruitmentResponse(
            recruitment.getId(),
            recruitment.getMatchingId(),
            matching.getCourtId(),
            matching.getHostId(),
            matching.getDate(),
            matching.getStartTime(),
            matching.getEndTime(),
            matching.getStatus(),
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
    }
}

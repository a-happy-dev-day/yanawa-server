package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
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
        Matching matching = new Matching.Builder()
                .hostId(requsest.getHostId())
                .courtId(requsest.getCourtId())
                .date(requsest.getDate())
                .startTime(requsest.getStartTime())
                .endTime(requsest.getEndTime())
                .status(MatchingStatusType.WAITING)
                .build();
        Long courtId = matching.getCourtId();
        courtRepository.findCourtNameById(courtId).orElseThrow(() -> new NoCourtDataException("코트장 정보를 조회할 수 없습니다."));
        Matching savedMatching = matchingService.createMatching(matching);

        Recruitment recruitment = new Recruitment.Builder()
                .matchingId(savedMatching.getId())
                .maximumLevel(requsest.getMaximumLevel())
                .minimumLevel(requsest.getMinimumLevel())
                .ageOfRecruitment(requsest.getAgeOfRecruitment())
                .sexOfRecruitment(requsest.getSexOfRecruitment())
                .preferenceGame(requsest.getPreferenceGame())
                .numberOfRecruitment(requsest.getNumberOfRecruitment())
                .costOfCourtPerPerson(requsest.getCostOfCourtPerPerson())
                .annual(requsest.getAnnual())
                .details(requsest.getDetails())
                .status(RecruitmentStatusType.OPENING)
                .build();
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);

        MatchingResponse response = new MatchingResponse.Builder()
                .recruitmentId(savedRecruitment.getId())
                .matchingId(savedMatching.getId())
                .courtId(savedMatching.getCourtId())
                .hostId(savedMatching.getHostId())
                .date(savedMatching.getDate())
                .startTime(savedMatching.getStartTime())
                .endTime(savedMatching.getEndTime())
                .matchingStatus(savedMatching.getStatus())
                .maximumLevel(savedRecruitment.getMaximumLevel())
                .minimumLevel(savedRecruitment.getMinimumLevel())
                .ageOfRecruitment(savedRecruitment.getAgeOfRecruitment())
                .sexOfRecruitment(savedRecruitment.getSexOfRecruitment())
                .preferenceGame(savedRecruitment.getPreferenceGame())
                .numberOfRecruitment(savedRecruitment.getNumberOfRecruitment())
                .setCostOfCourtPerPerson(savedRecruitment.getCostOfCourtPerPerson())
                .annual(savedRecruitment.getAnnual())
                .details(savedRecruitment.getDetails())
                .recruitmentStatus(savedRecruitment.getStatus())
                .build();

        return response;
    }


}

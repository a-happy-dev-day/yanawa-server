package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
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
                1L,
                new Level(4.0),
                new Level(1.5),
                AgeGroupType.TWENTIES,
                GenderType.NONE,
                PreferenceType.RALLY,
                3,
                2.0,
                AnnualType.FIVE_YEARS_LESS,
                "4명이서 랠리해요~",
                RecruitmentStatusType.OPENING
        );
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);

        return new MatchingResponse.Builder()
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
    }


}

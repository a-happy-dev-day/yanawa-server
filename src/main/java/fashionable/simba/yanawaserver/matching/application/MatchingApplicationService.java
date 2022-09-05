package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;

public class MatchingApplicationService {
    MatchingRepository matchingRepository;
    RecruitmentRepository recruitmentRepository;

    public MatchingResponse createRecruitment(MatchingRequsest requsest) {
        Matching matching = new Matching.Builder()
                .hostId(requsest.getHostId())
                .courtId(requsest.getCourtId())
                .date(requsest.getDate())
                .startTime(requsest.getStartTime())
                .endTime(requsest.getEndTime())
                .status(MatchingStatusType.WAITING)
                .build();
        Matching savedMatching = matchingRepository.save(matching);

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
        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        MatchingResponse response = new MatchingResponse.Builder()
                .recruitmentId(recruitment.getId())
                .matchingId(matching.getId())
                .courtId(matching.getCourtId())
                .hostId(matching.getHostId())
                .date(matching.getDate())
                .startTime(matching.getStartTime())
                .endTime(matching.getEndTime())
                .matchingStatus(matching.getStatus())
                .maximumLevel(recruitment.getMaximumLevel())
                .minimumLevel(recruitment.getMinimumLevel())
                .ageOfRecruitment(recruitment.getAgeOfRecruitment())
                .sexOfRecruitment(recruitment.getSexOfRecruitment())
                .preferenceGame(recruitment.getPreferenceGame())
                .numberOfRecruitment(recruitment.getNumberOfRecruitment())
                .setCostOfCourtPerPerson(recruitment.getCostOfCourtPerPerson())
                .annual(recruitment.getAnnual())
                .details(recruitment.getDetails())
                .recruitmentStatus(recruitment.getStatus())
                .build();

        return response;
    }
}

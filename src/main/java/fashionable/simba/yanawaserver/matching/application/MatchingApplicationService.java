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
        Recruitment recruitment = recruitmentRepository.save(requsest);

        Matching matching = matchingRepository.save(requsest);
        matching.setStatus(MatchingStatusType.WAITING);

        recruitment.setMatchingId(matching.getId());
        recruitment.setStatus(RecruitmentStatusType.OPENING);

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

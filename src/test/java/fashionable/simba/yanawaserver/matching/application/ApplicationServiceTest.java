package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ApplicationServiceTest {
    MatchingService matchingService;
    RecruitmentService recruitmentService;
    CourtRepository courtRepository;


    @BeforeEach
    public void setUp() {
    }

    @Test
    void createMatchingAndRecruitment_test() {
        MatchingRequsest requsest = new MatchingRequsest.Builder()
                .courtId(1L)
                .hostId(1L)
                .date(LocalDate.of(2022, 7, 29))
                .startTime(LocalTime.of(19, 0))
                .endTime(LocalTime.of(21, 0))
                .matchingStatus(MatchingStatusType.ONGOING)
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(1.5))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(3)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .details("4명이서 랠리해요~")
                .recruitmentStatus(RecruitmentStatusType.OPENING)
                .build();


    }
}

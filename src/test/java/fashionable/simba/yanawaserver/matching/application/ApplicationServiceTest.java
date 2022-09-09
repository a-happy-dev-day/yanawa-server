package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;
import fashionable.simba.yanawaserver.matching.repository.MemoryCourtRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationServiceTest {
    MatchingRepository matchingRepository = new MemoryMatchingRepository();
    RecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    ParticipationRepository participationRepository;
    MatchingService matchingService = new MatchingService(matchingRepository, recruitmentRepository);
    RecruitmentService recruitmentService = new RecruitmentService(recruitmentRepository, participationRepository);
    CourtRepository courtRepository = new MemoryCourtRepository();
    MatchingApplicationService applicationService = new MatchingApplicationService(matchingService, recruitmentService, courtRepository);

    @BeforeEach
    public void setUp() {
        matchingRepository.clear();
        recruitmentRepository.clear();
        courtRepository.save("서울테니스장");
    }

    @Test
    @DisplayName("매칭과 모집을 생성한다.")
    void createMatchingAndRecruitment_test() {
        MatchingRequsest requsest = getRequsest(1L);
        MatchingResponse response = applicationService.createMatchingAndRecruitment(requsest);

        assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getMatchingId()).isEqualTo(response.getMatchingId());
    }

    @Test
    @DisplayName("매칭,모집 생성시, 코트장 정보가 없으면 NoCourtDataException 발생한다.")
    void createMatchingAndRecruitment_nonCourtData() {
        MatchingRequsest requsest = getRequsest(2L);
        assertThrows(NoCourtDataException.class, () -> {
            applicationService.createMatchingAndRecruitment(requsest);
        });
    }


    private static MatchingRequsest getRequsest(long courtId) {
        return new MatchingRequsest.Builder()
                .courtId(courtId)
                .hostId(1L)
                .date(LocalDate.of(2022, 7, 29))
                .startTime(LocalTime.of(19, 0))
                .endTime(LocalTime.of(21, 0))
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(1.5))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(3)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .details("4명이서 랠리해요~")
                .build();
    }
}

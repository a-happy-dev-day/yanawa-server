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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationServiceTest {
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

        assertAll(
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getCourtId()).isEqualTo(response.getCourtId()),
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getHostId()).isEqualTo(response.getHostId()),
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getDate()).isEqualTo(response.getDate()),
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getStartTime()).isEqualTo(response.getStartTime()),
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getEndTime()).isEqualTo(response.getEndTime()),
                () -> assertThat(matchingRepository.findMatchingById(response.getMatchingId()).orElseThrow().getStatus()).isEqualTo(response.getMatchingStatus()),

                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getMatchingId()).isEqualTo(response.getMatchingId()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getMaximumLevel()).isEqualTo(response.getMaximumLevel()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getMinimumLevel()).isEqualTo(response.getMinimumLevel()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getAgeOfRecruitment()).isEqualTo(response.getAgeOfRecruitment()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getSexOfRecruitment()).isEqualTo(response.getSexOfRecruitment()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getPreferenceGame()).isEqualTo(response.getPreferenceGame()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getNumberOfRecruitment()).isEqualTo(response.getNumberOfRecruitment()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getAnnual()).isEqualTo(response.getAnnual()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getCostOfCourtPerPerson()).isEqualTo(response.getCostOfCourtPerPerson()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getStatus()).isEqualTo(response.getRecruitmentStatus()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(response.getRecruitmentId()).orElseThrow().getDetails()).isEqualTo(response.getDetails())
        );
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
        return new MatchingRequsest(
                courtId,
                1L,
                LocalDate.of(2022, 7, 29),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                new Level(4.0),
                new Level(1.5),
                AgeGroupType.TWENTIES,
                GenderType.NONE,
                PreferenceType.RALLY,
                3,
                2.0,
                AnnualType.FIVE_YEARS_LESS,
                "4명이서 랠리해요~"
        );
    }
}

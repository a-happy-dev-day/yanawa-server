package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponse;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponses;
import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.repository.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.JpaMatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.JpaParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.JpaRecruitmentRepository;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;
import fashionable.simba.yanawaserver.matching.repository.MemoryCourtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationServiceTest {
    JpaMatchingRepository matchingRepository;
    JpaRecruitmentRepository recruitmentRepository;
    JpaParticipationRepository participationRepository;
    MatchingService matchingService;
    RecruitmentService recruitmentService;
    CourtRepository courtRepository;
    MatchingApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        matchingService = new MatchingService(matchingRepository, recruitmentRepository);
        recruitmentService = new RecruitmentService(recruitmentRepository, participationRepository);
        courtRepository = new MemoryCourtRepository();
        applicationService = new MatchingApplicationService(matchingService, recruitmentService, courtRepository);

        courtRepository.save("서울테니스장");
    }

    @Test
    @DisplayName("매칭과 모집을 생성한다.")
    void createMatchingAndRecruitment_test() {
        RecruitmentRequsest requsest = getRequsest(1L);
        RecruitmentResponse response = applicationService.createMatchingAndRecruitment(requsest);

        assertAll(
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getCourtId()).isEqualTo(response.getCourtId()),
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getHostId()).isEqualTo(response.getHostId()),
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getDate()).isEqualTo(response.getDate()),
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getStartTime()).isEqualTo(response.getStartTime()),
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getEndTime()).isEqualTo(response.getEndTime()),
                () -> assertThat(matchingRepository.findById(response.getMatchingId()).orElseThrow().getStatus()).isEqualTo(response.getMatchingStatus()),

                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getMatchingId()).isEqualTo(response.getMatchingId()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getMaximumLevel()).isEqualTo(response.getMaximumLevel()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getMinimumLevel()).isEqualTo(response.getMinimumLevel()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getAgeOfRecruitment()).isEqualTo(response.getAgeOfRecruitment()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getSexOfRecruitment()).isEqualTo(response.getSexOfRecruitment()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getPreferenceGame()).isEqualTo(response.getPreferenceGame()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getNumberOfRecruitment()).isEqualTo(response.getNumberOfRecruitment()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getAnnual()).isEqualTo(response.getAnnual()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getCostOfCourtPerPerson()).isEqualTo(response.getCostOfCourtPerPerson()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getStatus()).isEqualTo(response.getRecruitmentStatus()),
                () -> assertThat(recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow().getDetails()).isEqualTo(response.getDetails())
        );
    }

    @Test
    @DisplayName("모든 모집정보를 불러온다.")
    void find_all_recruitment_test() {
        RecruitmentRequsest requsest = getRequsest(1L);
        RecruitmentResponse response = applicationService.createMatchingAndRecruitment(requsest);

        RecruitmentResponses responses = applicationService.findAll();
        assertTrue(responses.getRecruitmentResponses().contains(response));
    }

    @Test
    @DisplayName("매칭에대한 모집정보를 불러온다.")
    void find_one_recruitment_test() {
        RecruitmentRequsest requsest = getRequsest(1L);
        RecruitmentResponse response = applicationService.createMatchingAndRecruitment(requsest);

        RecruitmentResponse target = applicationService.findOne(response.getMatchingId());
        assertThat(target.getRecruitmentId()).isEqualTo(response.getRecruitmentId());
    }

    @Test
    @DisplayName("매칭,모집 생성시, 코트장 정보가 없으면 NoCourtDataException 발생한다.")
    void createMatchingAndRecruitment_nonCourtData() {
        RecruitmentRequsest requsest = getRequsest(2L);
        assertThrows(NoCourtDataException.class, () -> {
            applicationService.createMatchingAndRecruitment(requsest);
        });
    }


    private static RecruitmentRequsest getRequsest(long courtId) {
        return new RecruitmentRequsest(
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

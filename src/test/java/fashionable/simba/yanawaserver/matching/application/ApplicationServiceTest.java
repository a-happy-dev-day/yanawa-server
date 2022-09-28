package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentRequsest;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponse;
import fashionable.simba.yanawaserver.matching.application.dto.RecruitmentResponses;
import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.domain.CourtRepository;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import fashionable.simba.yanawaserver.matching.domain.service.MatchingService;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.error.NoCourtDataException;
import fashionable.simba.yanawaserver.matching.fake.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryRecruitmentRepository;
import fashionable.simba.yanawaserver.matching.infra.MemoryCourtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationServiceTest {
    MatchingRepository matchingRepository;
    RecruitmentRepository recruitmentRepository;
    ParticipationRepository participationRepository;
    CourtRepository courtRepository;

    MatchingService matchingService;
    RecruitmentService recruitmentService;
    MatchingApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        matchingRepository = new MemoryMatchingRepository();
        recruitmentRepository = new MemoryRecruitmentRepository();
        participationRepository = new MemoryParticipationRepository();
        courtRepository = new MemoryCourtRepository();

        matchingService = new MatchingService(matchingRepository, recruitmentRepository);
        recruitmentService = new RecruitmentService(recruitmentRepository, participationRepository);
        applicationService = new MatchingApplicationService(matchingService, recruitmentService, courtRepository);

        courtRepository.save("서울테니스장");
    }

    @Test
    @DisplayName("매칭과 모집을 생성한다.")
    void createMatchingAndRecruitment_test() {
        RecruitmentRequsest requsest = getRequsest(1L);
        RecruitmentResponse response = applicationService.createMatchingAndRecruitment(requsest);

        Matching matching = matchingRepository.findById(response.getMatchingId()).orElseThrow();
        Recruitment recruitment = recruitmentRepository.findById(response.getRecruitmentId()).orElseThrow();

        assertAll(
            () -> assertThat(matching.getCourtId()).isEqualTo(response.getCourtId()),
            () -> assertThat(matching.getHostId()).isEqualTo(response.getHostId()),
            () -> assertThat(matching.getDate()).isEqualTo(response.getDate()),
            () -> assertThat(matching.getStartTime()).isEqualTo(response.getStartTime()),
            () -> assertThat(matching.getEndTime()).isEqualTo(response.getEndTime()),
            () -> assertThat(matching.getStatus()).isEqualTo(response.getMatchingStatus()),

            () -> assertThat(recruitment.getMatchingId()).isEqualTo(response.getMatchingId()),
            () -> assertThat(recruitment.getMaximumLevel()).isEqualTo(response.getMaximumLevel()),
            () -> assertThat(recruitment.getMinimumLevel()).isEqualTo(response.getMinimumLevel()),
            () -> assertThat(recruitment.getAgeOfRecruitment()).isEqualTo(response.getAgeOfRecruitment()),
            () -> assertThat(recruitment.getSexOfRecruitment()).isEqualTo(response.getSexOfRecruitment()),
            () -> assertThat(recruitment.getPreferenceGame()).isEqualTo(response.getPreferenceGame()),
            () -> assertThat(recruitment.getNumberOfRecruitment()).isEqualTo(response.getNumberOfRecruitment()),
            () -> assertThat(recruitment.getAnnual()).isEqualTo(response.getAnnual()),
            () -> assertThat(recruitment.getCostOfCourtPerPerson()).isEqualTo(response.getCostOfCourtPerPerson()),
            () -> assertThat(recruitment.getStatus()).isEqualTo(response.getRecruitmentStatus()),
            () -> assertThat(recruitment.getDetails()).isEqualTo(response.getDetails())
        );
    }

    @Test
    @DisplayName("모든 모집정보를 불러온다.")
    void find_all_recruitment_test() {
        RecruitmentRequsest requsest = getRequsest(1L);
        RecruitmentResponse response = applicationService.createMatchingAndRecruitment(requsest);

        RecruitmentResponses responses = applicationService.findAll();
        assertThat(responses.getRecruitmentResponses().size()).isEqualTo(1);
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

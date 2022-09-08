package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.repository.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecruitmentServiceTest {
    MemoryRecruitmentRepository recruitmentRepository;
    RecruitmentService recruitmentService;

    @BeforeEach
    public void setUp() {
        recruitmentRepository = new MemoryRecruitmentRepository();
        recruitmentService = new RecruitmentService(recruitmentRepository);
    }

    @Test
    @DisplayName("모집을 생성한다.")
    void create_recuitment() {
        Recruitment recruitment = new Recruitment.Builder()
                .matchingId(1L)
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

        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);

        assertThat(recruitmentRepository.findRecruitmentById(savedRecruitment.getId()).orElseThrow()).isEqualTo(savedRecruitment);
    }

    @Test
    @DisplayName("진행자가 모집을 완료하면 모집을 완료한다.")
    void completeRecritument_test() {
        //given
        Recruitment recruitment = new Recruitment.Builder()
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(1.5))
                .ageOfRecruitment(AgeGroupType.TWENTIES)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.RALLY)
                .numberOfRecruitment(3)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.FIVE_YEARS_LESS)
                .details("4명이서 랠리해요~")
                .status(RecruitmentStatusType.OPENING)
                .build();
        //when
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);
        recruitmentService.completeRecritument(savedRecruitment.getId());
        //then
        assertThat(recruitmentRepository.findRecruitmentById(savedRecruitment.getId()).orElseThrow().getStatus()).isEqualTo(RecruitmentStatusType.CLOSED);
    }

    @Test
    @DisplayName("인원이 차면 모집을 완료한다.")
    void full_member_matching_recruitment() {
        //given
        //when
        //then
    }
}

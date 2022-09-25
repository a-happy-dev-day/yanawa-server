package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RecruitmentRepositoryTest {
    private static Logger logger = LoggerFactory.getLogger(RecruitmentRepositoryTest.class);

    RecruitmentRepository recruitmentRepository;

    @BeforeEach
    public void setUp() {
        recruitmentRepository = new MemoryRecruitmentRepository();
    }


    @Test
    void saveTest() {
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
        Long id = recruitmentRepository.save(recruitment).getId();
        assertAll(
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getMaximumLevel().getLevel()).isEqualTo(new Level(4.0).getLevel()),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getMinimumLevel().getLevel()).isEqualTo(new Level(1.5).getLevel()),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getAgeOfRecruitment()).isEqualTo(AgeGroupType.TWENTIES),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getSexOfRecruitment()).isEqualTo(GenderType.NONE),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getPreferenceGame()).isEqualTo(PreferenceType.RALLY),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getNumberOfRecruitment()).isEqualTo(3),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getCostOfCourtPerPerson()).isEqualTo(2.0),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getAnnual()).isEqualTo(AnnualType.FIVE_YEARS_LESS),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getDetails()).isEqualTo("4명이서 랠리해요~"),
                () -> assertThat(recruitmentRepository.findById(id).orElseThrow().getStatus()).isEqualTo(RecruitmentStatusType.OPENING)
        );
    }

    @Test
    void findTest() {
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
        Long id = recruitmentRepository.save(recruitment).getId();
        assertThat(recruitmentRepository.findById(id).orElseThrow().getId()).isEqualTo(id);
    }
}

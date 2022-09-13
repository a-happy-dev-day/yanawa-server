package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RecruitmentRepositoryTest {
    private static Logger logger = LoggerFactory.getLogger(RecruitmentRepositoryTest.class);

    RecruitmentRepository recruitmentRepository;

    @BeforeEach
    public void setUp() {
        recruitmentRepository = new MemoryRecruitmentRepository();
    }


    @Test
    void saveTest() {
        Recruitment recruitment = new Recruitment.Builder()
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(2.0))
                .ageOfRecruitment(AgeGroupType.ETC)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.MATCHING)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.NONE)
                .build();
        Long id = recruitmentRepository.save(recruitment).getId();
        assertAll(
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getMaximumLevel().getLevel()).isEqualTo(new Level(4.0).getLevel()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getMinimumLevel().getLevel()).isEqualTo(new Level(2.0).getLevel()),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getAgeOfRecruitment()).isEqualTo(AgeGroupType.ETC),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getSexOfRecruitment()).isEqualTo(GenderType.NONE),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getPreferenceGame()).isEqualTo(PreferenceType.MATCHING),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getNumberOfRecruitment()).isEqualTo(4),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getCostOfCourtPerPerson()).isEqualTo(2.0),
                () -> assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getAnnual()).isEqualTo(AnnualType.NONE)
        );
    }

    @Test
    void findTest() {
        Recruitment recruitment = new Recruitment.Builder()
                .maximumLevel(new Level(4.0))
                .minimumLevel(new Level(2.0))
                .ageOfRecruitment(AgeGroupType.ETC)
                .sexOfRecruitment(GenderType.NONE)
                .preferenceGame(PreferenceType.MATCHING)
                .numberOfRecruitment(4)
                .costOfCourtPerPerson(2.0)
                .annual(AnnualType.NONE)
                .build();
        Long id = recruitmentRepository.save(recruitment).getId();
        assertThat(recruitmentRepository.findRecruitmentById(id).orElseThrow().getId()).isEqualTo(id);
    }
}

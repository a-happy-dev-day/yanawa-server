package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.fake.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecruitmentServiceTest {
    RecruitmentRepository recruitmentRepository;
    ParticipationRepository participationRepository;
    RecruitmentService recruitmentService;

    @BeforeEach
    public void setUp() {
        recruitmentRepository = new MemoryRecruitmentRepository();
        participationRepository = new MemoryParticipationRepository();
        recruitmentService = new RecruitmentService(recruitmentRepository, participationRepository);
    }

    @Test
    @DisplayName("모집을 생성한다.")
    void create_recuitment() {
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

        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);

        assertThat(recruitmentRepository.findById(savedRecruitment.getId()).orElseThrow()).isEqualTo(savedRecruitment);
    }

    @Test
    @DisplayName("진행자가 모집을 완료하면 모집을 완료한다.")
    void completeRecritument_test() {
        //given
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
        Participation participation = new Participation(
            1L,
            1L,
            1L,
            LocalDateTime.of(2022, 9, 1, 18, 0),
            ParticipationStatusType.WAITING
        );
        participationRepository.save(participation);
        //when
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);
        recruitmentService.completeRecritument(savedRecruitment.getId());
        //then
        assertThat(recruitmentRepository.findById(savedRecruitment.getId()).orElseThrow().getStatus()).isEqualTo(RecruitmentStatusType.CLOSED);
    }

    @Test
    @DisplayName("모집을 매칭아이디로 검색한다.")
    void find_recruitment_test() {
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
        Recruitment save = recruitmentService.createRecruitment(recruitment);
        assertThat(recruitmentRepository.findByMatchingId(1L).orElseThrow()).isEqualTo(save);
    }

    @Test
    @DisplayName("모집을 종료할때 참가자가 없으면 IllegalArgumentException 발생한다.")
    void completeRecritument_thorw_exception_test() {
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
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);
        Long savedRecruitmentId = savedRecruitment.getId();
        assertThrows(IllegalArgumentException.class, () -> {
            recruitmentService.completeRecritument(savedRecruitmentId);
        });
    }
}

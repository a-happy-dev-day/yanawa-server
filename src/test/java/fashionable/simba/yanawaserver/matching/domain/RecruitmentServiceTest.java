package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.service.RecruitmentService;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecruitmentServiceTest {
    MemoryRecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    ParticipationRepository participationRepository = new MemoryParticipationRepository();
    RecruitmentService recruitmentService = new RecruitmentService(recruitmentRepository, participationRepository);

    @BeforeEach
    public void setUp() {
        recruitmentRepository.clear();
        participationRepository.clear();
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
                .status(RecruitmentStatusType.OPENING)
                .build();
        Participation participation = new Participation.Builder()
                .matchingId(1L)
                .build();
        participationRepository.save(participation);
        //when
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);
        recruitmentService.completeRecritument(savedRecruitment.getId());
        //then
        assertThat(recruitmentRepository.findRecruitmentById(savedRecruitment.getId()).orElseThrow().getStatus()).isEqualTo(RecruitmentStatusType.CLOSED);
    }

    @Test
    @DisplayName("모집을 매칭아이디로 검색한다.")
    void find_recruitment_test() {
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
                .status(RecruitmentStatusType.OPENING)
                .build();
        Recruitment save = recruitmentService.createRecruitment(recruitment);
        assertThat(recruitmentRepository.findRecruitmentByMatchingId(1L).orElseThrow()).isEqualTo(save);
    }

    @Test
    @DisplayName("모집을 종료할때 참가자가 없으면 IllegalArgumentException 발생한다.")
    void completeRecritument_thorw_exception_test() {
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
        Recruitment savedRecruitment = recruitmentService.createRecruitment(recruitment);
        assertThrows(IllegalArgumentException.class, () -> {
            recruitmentService.completeRecritument(savedRecruitment.getId());
        });
    }
}

package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.RecruitmentRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryRecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParticipationServiceTest {
    ParticipationRepository participationRepository = new MemoryParticipationRepository();
    RecruitmentRepository   recruitmentRepository = new MemoryRecruitmentRepository();
    ParticipationService    participationService = new ParticipationService(participationRepository, recruitmentRepository);

    @BeforeEach
    void setUp() {
        participationRepository.clear();
        recruitmentRepository.clear();

        Recruitment openingRecruitment = new Recruitment(1L, new Level(4.0), new Level(1.5), AgeGroupType.TWENTIES, GenderType.NONE,
                PreferenceType.RALLY, 3, 2.0, AnnualType.FIVE_YEARS_LESS, "4명이서 랠리해요~", RecruitmentStatusType.OPENING);
        recruitmentRepository.save(openingRecruitment);
        Recruitment closedRecruitment = new Recruitment(2L, new Level(4.0), new Level(1.5), AgeGroupType.TWENTIES, GenderType.NONE,
                PreferenceType.RALLY, 3, 2.0, AnnualType.FIVE_YEARS_LESS, "4명이서 랠리해요~", RecruitmentStatusType.CLOSED);
        recruitmentRepository.save(closedRecruitment);
    }

    @DisplayName("사용자가 참여를 요청한다.")
    @Test
    void createParticipationTest() {
        Participation participation = getParticipation(1L, 1L);
        Participation save = participationService.createParticipation(participation);

        assertThat(save.getId()).isEqualTo(1L);
    }

    @DisplayName("참여요청에 대한 모집이 종료되었을 경우, IllegalArgumentExceptiond이 발생한다.")
    @Test
    void closedRecruitment_to_participation_test() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    participationService.createParticipation(getParticipation(1L, 2L));
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    participationService.createParticipation(getParticipation(1L, 3L));
                })
        );
    }

    @DisplayName("참여요청에 대해 해당 모집에 승인, 거절 이력이 있다면, IllegalArgumentExceptiond이 발생한다.")
    @Test
    void before_participtaion_test() {
        Participation participation1 = getParticipation(1L, 1L);
        Participation save1 = participationService.createParticipation(participation1);
        participationService.acceptParticipation(save1.getId());

        Participation participation2 = getParticipation(2L, 1L);
        Participation save2 = participationService.createParticipation(participation2);
        participationService.rejectParticipation(save2.getId());

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    participationService.createParticipation(participation1);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    participationService.createParticipation(participation2);
                })
        );

    }

    @DisplayName("진행자가 참여를 수락한다.")
    @Test
    void acceptParticipationTest() {
        Participation participation = getParticipation(1L, 1L);
        Participation save = participationService.createParticipation(participation);

        Participation accepteSave = participationService.acceptParticipation(save.getId());

        assertThat(accepteSave.getStatus()).isEqualTo(ParticipationStatusType.ACCEPTED);
    }

    @DisplayName("참여수락에서 참여가 대기(WAITING)이 아니라면, IllegalArgumentException발생한다.")
    @Test
    void acceptParticipation_notWaiting_test() {
        Participation participation = getParticipation(1L, 1L);
        Participation save = participationService.createParticipation(participation);

        Participation acceptSave = participationService.acceptParticipation(save.getId());
        Long acceptSaveId = acceptSave.getId();
        assertThrows(IllegalArgumentException.class, () -> {
            participationService.acceptParticipation(acceptSaveId);
        });
    }

    @DisplayName("참여거절에서 참여가 대기(WAITING)이 아니라면, IllegalArgumentException발생한다.")
    @Test
    void rejectParticipation_notWaiting_test() {
        Participation participation = getParticipation(1L, 1L);
        Participation save = participationService.createParticipation(participation);

        Participation acceptSave = participationService.acceptParticipation(save.getId());
        Long acceptSaveId = acceptSave.getId();
        assertThrows(IllegalArgumentException.class, () -> {
            participationService.rejectParticipation(acceptSaveId);
        });
    }

    @DisplayName("진행자가 참여를 거절한다.")
    @Test
    void rejectParticipationTest() {
        Participation participation = getParticipation(1L, 1L);
        Participation save = participationService.createParticipation(participation);

        Participation rejectSave = participationService.rejectParticipation(save.getId());

        assertThat(rejectSave.getStatus()).isEqualTo(ParticipationStatusType.REJECTED);
    }

    private static Participation getParticipation(Long userId, Long recruitmentId) {
        return new Participation(userId, recruitmentId, LocalDateTime.of(2022, 9, 1, 12, 0), ParticipationStatusType.WAITING);
    }

    private static Participation getParticipationStatus(Long userId, Long recruitmentId, ParticipationStatusType status) {
        return new Participation(userId, recruitmentId, LocalDateTime.of(2022, 9, 1, 12, 0), status);
    }
}

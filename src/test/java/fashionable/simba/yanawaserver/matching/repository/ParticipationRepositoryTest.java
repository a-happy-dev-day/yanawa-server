package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParticipationRepositoryTest {
    ParticipationRepository participationRepository = new MemoryParticipationRepository();

    @BeforeEach
    void setUp() {
        participationRepository.clear();
    }

    @Test
    @DisplayName("참가 신청 저장 테스트")
    void participation_save() {
        Participation participation = getParticipation();
        Participation savedParticipation = participationRepository.save(participation);
        assertThat(participationRepository.findParticipationById(savedParticipation.getRecruitmentId()).orElseThrow()).isEqualTo(savedParticipation);
    }

    @Test
    @DisplayName("참가 신청자 수 테스트")
    void count_participation_test() {
        Participation participation = getParticipation();
        Participation participation2 = getParticipation();
        participationRepository.save(participation);
        participationRepository.save(participation2);
        assertThat(participationRepository.countParticipationsByMatchingId(1L)).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자가 해당 모집에 이전에 보냈던 요청을 찾는다.")
    void findParticipationTest() {
        Participation participation = getParticipation();
        participationRepository.save(participation);

        assertAll(
            () -> assertThat(participationRepository.findParticipationByUserIdAndRecruitmentId(1L, 1L).orElseThrow().getId()).isEqualTo(1L),
            () -> assertTrue(participationRepository.findParticipationByUserIdAndRecruitmentId(2L, 1L).isEmpty()),
            () -> assertTrue(participationRepository.findParticipationByUserIdAndRecruitmentId(1L, 2L).isEmpty())
        );
    }

    private static Participation getParticipation() {
        return new Participation(
                1L,
                1L,
                1L,
                LocalDateTime.of(2022, 9, 1, 18, 0),
                ParticipationStatusType.WAITING
        );
    }
}

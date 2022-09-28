package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParticipationRepositoryTest {
    ParticipationRepository participationRepository;

    @BeforeEach
    void setUp() {
        participationRepository = new MemoryParticipationRepository();
    }

    @Test
    @DisplayName("참가 신청 저장 테스트")
    void participation_save() {
        Participation participation = new Participation(
            1L,
            1L,
            1L,
            LocalDateTime.of(2022, 9, 1, 18, 0),
            ParticipationStatusType.WAITING
        );
        Participation savedParticipation = participationRepository.save(participation);
        assertThat(participationRepository.findById(savedParticipation.getMatchingId()).orElseThrow()).isEqualTo(savedParticipation);
    }

    @Test
    @DisplayName("참가 신청자 수 테스트")
    void count_participation_test() {
        Participation participation = new Participation(
            1L,
            1L,
            1L,
            LocalDateTime.of(2022, 9, 1, 18, 0),
            ParticipationStatusType.WAITING
        );
        Participation participation2 = new Participation(
            1L,
            1L,
            1L,
            LocalDateTime.of(2022, 9, 1, 18, 0),
            ParticipationStatusType.WAITING
        );
        participationRepository.save(participation);
        participationRepository.save(participation2);
        assertThat(participationRepository.countParticipationsByMatchingId(1L)).isEqualTo(2);
    }
}

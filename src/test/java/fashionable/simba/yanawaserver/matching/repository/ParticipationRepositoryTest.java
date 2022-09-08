package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.repository.ParticipationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParticipationRepositoryTest {
    ParticipationRepository participationRepository = new MemoryParticipationRepository();

    @BeforeEach
    void setUp() {
        participationRepository.clear();
    }

    @Test
    @DisplayName("참가 신청 저장 테스트")
    void participation_save() {
        Participation participation = new Participation.Builder()
                .userId(1L)
                .matchingId(1L)
                .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                .status(ParticipationStatusType.WAITING)
                .build();
        Participation savedParticipation = participationRepository.save(participation);
        assertThat(participationRepository.findParticipationById(savedParticipation.getMatchingId()).orElseThrow()).isEqualTo(savedParticipation);
    }

    @Test
    @DisplayName("참가 신청자 수 테스트")
    void count_participation_test() {
        Participation participation = new Participation.Builder()
                .userId(1L)
                .matchingId(1L)
                .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                .status(ParticipationStatusType.WAITING)
                .build();
        Participation participation2 = new Participation.Builder()
                .userId(2L)
                .matchingId(1L)
                .requestDateTime(LocalDateTime.of(2022, 8, 22, 19, 30))
                .status(ParticipationStatusType.WAITING)
                .build();
        participationRepository.save(participation);
        participationRepository.save(participation2);
        assertThat(participationRepository.countParticipationsByMatchingId(1L)).isEqualTo(2);
    }
}

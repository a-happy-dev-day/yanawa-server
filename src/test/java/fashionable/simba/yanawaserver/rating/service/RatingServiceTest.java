package fashionable.simba.yanawaserver.rating.service;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryParticipationRepository;
import fashionable.simba.yanawaserver.matching.fake.MemoryRecruitmentRepository;
import fashionable.simba.yanawaserver.rating.domain.MannerTemperatureType;
import fashionable.simba.yanawaserver.rating.domain.Rating;
import fashionable.simba.yanawaserver.rating.dto.RatingRequest;
import fashionable.simba.yanawaserver.rating.fake.FakeRatingRepository;
import fashionable.simba.yanawaserver.rating.infra.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RatingServiceTest {
    RatingRepository ratingRepository = new FakeRatingRepository();
    RecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    ParticipationRepository participationRepository = new MemoryParticipationRepository();

    RatingService ratingService;
    Recruitment recruitment;
    Participation participation1;
    Participation participation2;
    Participation participation3;


    @BeforeEach
    void setUp() {

        participation1 = new Participation(1L, 1L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);
        participation2 = new Participation(1L, 2L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);
        participation3 = new Participation(1L, 3L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);

        recruitmentRepository.save(recruitment);
        participationRepository.save(participation1);
        participationRepository.save(participation2);
        participationRepository.save(participation3);

        ratingService = new RatingService(ratingRepository, recruitmentRepository, participationRepository);
    }

    @Test
    @DisplayName("사용자가 참여자의 능력을 평가한다")
    void createRating() {
        RatingRequest request = new RatingRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");

        Rating savedRating = ratingService.createRating(request);

        assertThat(ratingRepository.findById(savedRating.getId()).orElseThrow()).isEqualTo(savedRating);
    }

    @Test
    @DisplayName("참여자와 사용자는 해당 모집의 일원이 아닐경우, 예외가 발생한다.")
    void not_include_recruitment_test() {
        RatingRequest request1 = new RatingRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 10L, "후기");
        RatingRequest request2 = new RatingRequest(2L, 10L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");
        RatingRequest request3 = new RatingRequest(3L, 1L, 10L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(request1)),
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(request2)),
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(request3))
        );

    }

    @Test
    @DisplayName("상대방의 평가 점수를 조회한다.")
    void find_average_ratingScore() {
        //given
        RatingRequest request1 = new RatingRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");
        ratingService.createRating(request1);
        RatingRequest request2 = new RatingRequest(2L, 1L, 1L, BigDecimal.valueOf(4.0), MannerTemperatureType.EXCELLENT, 3L, "후기");
        ratingService.createRating(request2);

        assertThat(ratingService.findAverageRating(1L)).isEqualTo(BigDecimal.valueOf(3.5));
    }


}

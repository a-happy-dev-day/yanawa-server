package fashionable.simba.yanawaserver.review;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RatingServiceTest {
    RatingRepository ratingRepository = new FakeRatingRepository();
    @Mock
    RecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    @Mock
    ParticipationRepository participationRepository = new MemoryParticipationRepository();

    RatingService ratingService;
    Recruitment recruitment;
    Participation participation1;
    Participation participation2;


    @BeforeEach
    void setUp() {
        recruitment = new Recruitment(1L, 1L, new Level(2.0), new Level(1.0), AgeGroupType.TWENTIES, GenderType.NONE, PreferenceType.MATCHING, 3, 2.0, AnnualType.NONE, "123", RecruitmentStatusType.CLOSED);
        participation1 = new Participation(1L, 1L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);
        participation2 = new Participation(1L, 2L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);

        recruitmentRepository.save(recruitment);
        participationRepository.save(participation1);
        participationRepository.save(participation2);

        ratingService = new RatingService(ratingRepository, recruitmentRepository, participationRepository);
    }

    @Test
    @DisplayName("사용자가 참여자의 능력을 평가한다")
    void createRating() {
        Rating rating = new Rating(1L, 1L, 1L, 3.5, 1L, 2L, "후기");

        Rating savedRating = ratingService.createRating(rating);

        assertThat(ratingRepository.findById(savedRating.getId()).orElseThrow()).isEqualTo(savedRating);
    }

    @Test
    @DisplayName("참여자와 사용자는 해당 모집의 일원이 아닐경우, 예외가 발생한다.")
    void not_include_recruitment_test() {
        Rating rating1 = new Rating(1L, 1L, 2L, 3.5, 1L, 2L, "후기");
        Rating rating2 = new Rating(1L, 3L, 1L, 3.5, 1L, 2L, "후기");
        Rating rating3 = new Rating(1L, 1L, 1L, 3.5, 1L, 3L, "후기");

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(rating1)),
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(rating2)),
            () -> assertThrows(IllegalArgumentException.class, () -> ratingService.createRating(rating3))
        );

    }

    @Test
    @DisplayName("상대방의 평가 점수를 조회한다.")
    void search_rating_score() {

    }


}

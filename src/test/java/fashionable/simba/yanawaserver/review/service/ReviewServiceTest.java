package fashionable.simba.yanawaserver.review.service;

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
import fashionable.simba.yanawaserver.review.domain.MannerTemperatureType;
import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.dto.ReviewRequest;
import fashionable.simba.yanawaserver.review.exception.NoMatchingDataException;
import fashionable.simba.yanawaserver.review.exception.NoParticipationDataException;
import fashionable.simba.yanawaserver.review.fake.FakeReviewRepository;
import fashionable.simba.yanawaserver.review.infra.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReviewServiceTest {
    ReviewRepository reviewRepository = new FakeReviewRepository();
    RecruitmentRepository recruitmentRepository = new MemoryRecruitmentRepository();
    ParticipationRepository participationRepository = new MemoryParticipationRepository();

    ReviewService reviewService;
    Recruitment recruitment = new Recruitment(1L, 1L, new Level(2.0), new Level(1.0), AgeGroupType.TWENTIES, GenderType.NONE, PreferenceType.MATCHING, 3, 2.0, AnnualType.NONE, "123", RecruitmentStatusType.CLOSED);

    Participation participation1 = new Participation(1L, 1L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);
    Participation participation2 = new Participation(1L, 2L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);
    Participation participation3 = new Participation(1L, 3L, 1L, LocalDateTime.now(), ParticipationStatusType.ACCEPTED);


    @BeforeEach
    void setUp() {
        recruitmentRepository.save(recruitment);
        participationRepository.save(participation1);
        participationRepository.save(participation2);
        participationRepository.save(participation3);

        reviewService = new ReviewService(reviewRepository, recruitmentRepository, participationRepository);
    }

    @Test
    @DisplayName("사용자가 참여자의 능력을 평가한다")
    void createReview() {
        ReviewRequest request = new ReviewRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");

        Review savedReview = reviewService.createReview(request);

        assertThat(reviewRepository.findById(savedReview.getId()).orElseThrow()).isEqualTo(savedReview);
    }

    @Test
    @DisplayName("참여자와 사용자는 해당 모집의 일원이 아닐경우, 예외가 발생한다.")
    void not_include_recruitment_test() {
        ReviewRequest 작성자가참여X = new ReviewRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 10L, "후기");
        ReviewRequest 평가자가참여X = new ReviewRequest(2L, 10L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");
        ReviewRequest 모집정보X = new ReviewRequest(3L, 1L, 10L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");

        assertAll(
            () -> assertThrows(NoParticipationDataException.class, () -> reviewService.createReview(작성자가참여X)),
            () -> assertThrows(NoParticipationDataException.class, () -> reviewService.createReview(평가자가참여X)),
            () -> assertThrows(NoMatchingDataException.class, () -> reviewService.createReview(모집정보X))
        );

    }

}

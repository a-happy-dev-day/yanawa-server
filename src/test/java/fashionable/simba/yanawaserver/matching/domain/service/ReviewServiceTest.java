package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingReview;
import fashionable.simba.yanawaserver.matching.domain.repository.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.ReviewRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryMatchingRepository;
import fashionable.simba.yanawaserver.matching.repository.MemoryReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReviewServiceTest {
    ReviewRepository reviewRepository;
    MatchingRepository matchingRepository;
    ReviewService reviewService;

    Matching 종료된매칭 = getMatching(MatchingStatusType.FINISHED);

    @BeforeEach
    void setUp() {
        reviewRepository = new MemoryReviewRepository();
        matchingRepository = new MemoryMatchingRepository();
        matchingRepository.save(종료된매칭);
        reviewService = new ReviewService(reviewRepository, matchingRepository);
    }

    @Test
    @DisplayName("리뷰 생성")
    void create() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, "테스트후기");

        MatchingReview savedReview = reviewService.createReview(review);
        Long savedReviewId = savedReview.getId();

        assertThat(reviewRepository.findById(savedReviewId).orElseThrow().getId()).isEqualTo(savedReviewId);
    }

    @ParameterizedTest
    @EnumSource(value = MatchingStatusType.class, names = {"FINISHED"}, mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("리뷰 생성시 해당 매칭이 종료되지 않았다면, IllegalArgumentException이 발생한다.")
    void create_not_finised_matching_exception_test(MatchingStatusType statusType) {
        Matching matching = getMatching(statusType);
        Matching savedMatching = matchingRepository.save(matching);
        Long savedMatchingId = savedMatching.getId();

        MatchingReview review = new MatchingReview(savedMatchingId, 1L, 1L, "후기 테스트");

        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.createReview(review);
        });

    }

    private static Matching getMatching(MatchingStatusType waiting) {
        return new Matching(
                1L,
                1L,
                1L,
                LocalDate.of(2022, 9, 1),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                waiting
        );
    }
}

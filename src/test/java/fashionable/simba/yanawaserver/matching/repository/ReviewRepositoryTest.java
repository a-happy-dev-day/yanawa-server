package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingReview;
import fashionable.simba.yanawaserver.matching.domain.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReviewRepositoryTest {
    ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = new MemoryReviewRepository();
    }

    @Test
    @DisplayName("리뷰 생성")
    void create() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, 2L, "리뷰테스트");

        MatchingReview savedReciew = reviewRepository.save(review);

        Long savedReviewId = savedReciew.getId();
        assertAll(
                () -> assertThat(reviewRepository.findById(savedReviewId).orElseThrow().getMatchingId()).isEqualTo(1L),
                () -> assertThat(reviewRepository.findById(savedReviewId).orElseThrow().getWriterId()).isEqualTo(1L),
                () -> assertThat(reviewRepository.findById(savedReviewId).orElseThrow().getPartnerId()).isEqualTo(2L),
                () -> assertThat(reviewRepository.findById(savedReviewId).orElseThrow().getDetails()).isEqualTo("리뷰테스트")
        );
    }

    @Test
    @DisplayName("매칭 아이디로 리뷰 찾기")
    void findByMatchingId() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, 2L, "리뷰테스트");
        Long savedReviewId = reviewRepository.save(review).getId();

        assertThat(reviewRepository.findByMatchingId(1L).orElseThrow().getId()).isEqualTo(savedReviewId);
    }

    @Test
    @DisplayName("작성자 아이디로 리뷰 찾기")
    void findByWriterId() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, 2L, "리뷰테스트");
        Long savedReviewId = reviewRepository.save(review).getId();

        assertThat(reviewRepository.findByWriterId(1L).orElseThrow().getId()).isEqualTo(savedReviewId);
    }

    @Test
    @DisplayName("평가자 아이디로 리뷰 찾기")
    void findByPartnerId() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, 2L, "리뷰테스트");
        Long savedReviewId = reviewRepository.save(review).getId();

        assertThat(reviewRepository.findByPartnerId(2L).orElseThrow().getId()).isEqualTo(savedReviewId);
    }
}

package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingReview;
import fashionable.simba.yanawaserver.matching.domain.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("매칭아이디&작성자아이디&평가자아이디로 리뷰 찾기")
    void findByMatchingIdAndWriterIdAndPartnerId() {
        MatchingReview review = new MatchingReview(1L, 1L, 1L, 2L, "리뷰테스트");
        Long savedReviewId = reviewRepository.save(review).getId();

        assertAll(
                () -> assertThat(reviewRepository.findByMatchingIdAndWriterIdAndPartnerId(1L, 1L, 2L).orElseThrow().getId())
                        .isEqualTo(savedReviewId),
                () -> assertTrue(reviewRepository.findByMatchingIdAndWriterIdAndPartnerId(2L, 1L, 2L).isEmpty()),
                () -> assertTrue(reviewRepository.findByMatchingIdAndWriterIdAndPartnerId(1L, 2L, 2L).isEmpty()),
                () -> assertTrue(reviewRepository.findByMatchingIdAndWriterIdAndPartnerId(1L, 1L, 3L).isEmpty())
        );

    }

}

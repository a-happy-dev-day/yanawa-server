package fashionable.simba.yanawaserver.matching.domain.service;

import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.Matching;
import fashionable.simba.yanawaserver.matching.domain.MatchingReview;
import fashionable.simba.yanawaserver.matching.domain.repository.MatchingRepository;
import fashionable.simba.yanawaserver.matching.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MatchingRepository matchingRepository;

    public ReviewService(ReviewRepository reviewRepository, MatchingRepository matchingRepository) {
        this.reviewRepository = reviewRepository;
        this.matchingRepository = matchingRepository;
    }

    public MatchingReview createReview(MatchingReview review) {
        Matching matching = matchingRepository.findMatchingById(review.getMatchingId()).orElseThrow();
        if (matching.getStatus() != MatchingStatusType.FINISHED) {
            throw new IllegalArgumentException("매칭이 좋료되지 않아 후기를 작성할 수 없습니다.");
        }
        return reviewRepository.save(review);
    }
}

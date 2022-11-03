package fashionable.simba.yanawaserver.review.fake;

import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.infra.ReviewRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeReviewRepository implements ReviewRepository {
    private final Map<Long, Review> reviews = new HashMap<>();

    @Override
    public Review save(Review review) {

        reviews.put(review.getId(), review);

        return review;
    }

    @Override
    public Optional<Review> findById(Long reviewId) {
        return Optional.ofNullable(reviews.get(reviewId));
    }

    @Override
    public List<Review> findByParticipantId(Long participantId) {
        List<Review> reviewList = reviews.values().stream().filter(x -> x.getParticipantId().equals(participantId)).collect(Collectors.toList());
        return reviewList;
    }

    @Override
    public List<Review> findByRecruitmentId(Long recruitmentId) {
        return null;
    }

    @Override
    public List<Review> findByWriterId(Long userId) {
        return null;
    }
}

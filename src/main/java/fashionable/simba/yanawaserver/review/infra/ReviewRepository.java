package fashionable.simba.yanawaserver.review.infra;

import fashionable.simba.yanawaserver.review.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);

    Optional<Review> findById(Long reviewId);

    List<Review> findByParticipantId(Long participantId);

    List<Review> findByRecruitmentId(Long recruitmentId);

    List<Review> findByUserId(Long userId);
}

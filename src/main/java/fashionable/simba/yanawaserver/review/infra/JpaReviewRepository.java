package fashionable.simba.yanawaserver.review.infra;

import fashionable.simba.yanawaserver.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long>, ReviewRepository {
    @Override
    Review save(Review review);

    @Override
    Optional<Review> findById(Long reviewId);

    @Override
    List<Review> findByParticipantId(Long participantId);

    @Override
    List<Review> findByRecruitmentId(Long recruitmentId);

    @Override
    List<Review> findByUserId(Long userId);

}

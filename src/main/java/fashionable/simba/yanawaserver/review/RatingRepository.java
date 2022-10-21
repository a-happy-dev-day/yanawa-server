package fashionable.simba.yanawaserver.review;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RatingRepository {
    Rating save(Rating rating);

    Optional<Rating> findById(Long ratingId);

    List<Rating> findByParticipantId(Long participantId);

    List<Rating> findByRequritmentId(Long requritmentId);

    List<Rating> findByUserId(Long userId);
}

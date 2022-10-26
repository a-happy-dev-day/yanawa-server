package fashionable.simba.yanawaserver.rating.infra;

import fashionable.simba.yanawaserver.rating.domain.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {
    Rating save(Rating rating);

    Optional<Rating> findById(Long ratingId);

    List<Rating> findByParticipantId(Long participantId);

    List<Rating> findByRecruitmentId(Long recruitmentId);

    List<Rating> findByUserId(Long userId);
}

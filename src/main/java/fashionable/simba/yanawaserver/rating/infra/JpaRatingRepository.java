package fashionable.simba.yanawaserver.rating.infra;

import fashionable.simba.yanawaserver.rating.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRatingRepository extends JpaRepository<Rating, Long>, RatingRepository {
    @Override
    Rating save(Rating rating);

    @Override
    Optional<Rating> findById(Long ratingId);

    @Override
    List<Rating> findByParticipantId(Long participantId);

    @Override
    List<Rating> findByRecruitmentId(Long recruitmentId);

    @Override
    List<Rating> findByUserId(Long userId);

}

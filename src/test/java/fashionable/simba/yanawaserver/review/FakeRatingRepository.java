package fashionable.simba.yanawaserver.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeRatingRepository implements RatingRepository{
    private final Map<Long, Rating> ratings = new HashMap<>();

    @Override
    public Rating save(Rating rating) {

        ratings.put(rating.getId(), rating);

        return rating;
    }

    @Override
    public Optional<Rating> findById(Long ratingId) {
        return Optional.ofNullable(ratings.get(ratingId));
    }

    @Override
    public List<Rating> findByParticipantId(Long participantId) {
        List<Rating> ratingList = ratings.values().stream().filter(x -> x.getParticipantId().equals(participantId)).collect(Collectors.toList());
        return ratingList;
    }

    @Override
    public List<Rating> findByRequritmentId(Long requritmentId) {
        return null;
    }

    @Override
    public List<Rating> findByUserId(Long userId) {
        return null;
    }
}

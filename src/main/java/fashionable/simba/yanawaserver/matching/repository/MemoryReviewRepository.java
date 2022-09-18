package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingReview;
import fashionable.simba.yanawaserver.matching.domain.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryReviewRepository implements ReviewRepository {
    private static final Map<Long, MatchingReview> reviews = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public MatchingReview save(MatchingReview review) {
        Long id = getId();
        MatchingReview save = new MatchingReview(
                id,
                review.getMatchingId(),
                review.getWriterId(),
                review.getPartnerId(),
                review.getDetails()
        );
        reviews.put(id, save);
        return save;
    }

    @Override
    public Optional<MatchingReview> findById(Long id) {
        return Optional.ofNullable(reviews.get(id));
    }

    @Override
    public Optional<MatchingReview> findByMatchingId(Long id) {
        return reviews.values().stream().filter(x -> x.getMatchingId().equals(id)).findAny();
    }

    @Override
    public Optional<MatchingReview> findByWriterId(Long id) {
        return reviews.values().stream().filter(x -> x.getWriterId().equals(id)).findAny();
    }

    @Override
    public Optional<MatchingReview> findByPartnerId(Long id) {
        return reviews.values().stream().filter(x -> x.getPartnerId().equals(id)).findAny();
    }

    private synchronized Long getId() {
        return ++sequence;
    }

}

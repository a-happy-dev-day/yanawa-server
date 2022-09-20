package fashionable.simba.yanawaserver.matching.domain.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingReview;

import java.util.Optional;

public interface ReviewRepository {
    MatchingReview save(MatchingReview review);

    Optional<MatchingReview> findById(Long id);

    Optional<MatchingReview> findByMatchingIdAndWriterIdAndPartnerId(Long mathicngId, Long writerId, Long partnerId);
}

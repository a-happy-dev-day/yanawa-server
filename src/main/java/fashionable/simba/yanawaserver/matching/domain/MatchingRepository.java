package fashionable.simba.yanawaserver.matching.domain;

public interface MatchingRepository {
    void save(Matching matching);

    Matching findMatchingById(Long matchingId);
}

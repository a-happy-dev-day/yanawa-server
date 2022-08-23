package fashionable.simba.yanawaserver.matching.domain;

import java.util.Optional;

public interface MatchingApplyRepository {
    void save(MatchingApply apply);

    Optional<MatchingApply> findMatchingApplyById(Long id);

    Integer countAppliesdById(Long id);
}

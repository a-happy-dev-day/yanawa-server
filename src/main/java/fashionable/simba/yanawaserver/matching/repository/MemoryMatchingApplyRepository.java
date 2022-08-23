package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingApply;
import fashionable.simba.yanawaserver.matching.domain.MatchingApplyRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryMatchingApplyRepository implements MatchingApplyRepository {
    public static Map<Long, MatchingApply> applies = new HashMap<>();

    @Override
    public void save(MatchingApply apply) {
        applies.put(apply.getId(), apply);
    }

    @Override
    public Optional<MatchingApply> findMatchingApplyById(Long id) {
        return Optional.ofNullable(applies.get(id));
    }

    @Override
    public Integer countAppliesdById(Long id) {
        return (int) applies.values().stream().filter(x -> x.getMatchingId().equals(id)).count();
    }
}

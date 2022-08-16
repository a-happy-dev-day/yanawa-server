package fashionable.simba.yanawaserver.matching.repository;

import fashionable.simba.yanawaserver.matching.domain.MatchingApply;

import java.util.HashMap;
import java.util.Map;

public class MatchingApplyRepository {
    public static Map<Long, MatchingApply> applies = new HashMap<>();

    public static void save(Long applyId, MatchingApply apply) {
        applies.put(applyId, apply);
    }
}

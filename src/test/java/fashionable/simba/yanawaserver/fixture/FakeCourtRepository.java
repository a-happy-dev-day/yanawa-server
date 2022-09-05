package fashionable.simba.yanawaserver.fixture;

import java.util.HashMap;
import java.util.Map;

public class FakeCourtRepository {
    private final Map<Long, String> courts = new HashMap<>() {{
        put(0L, "서울 테니스장");
        put(1L, "부산 테니스장");
        put(2L, "올림픽 테니스장");
    }};
}

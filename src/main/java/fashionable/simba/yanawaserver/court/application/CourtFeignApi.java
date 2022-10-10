package fashionable.simba.yanawaserver.court.application;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CourtFeignApi {
    ResponseEntity<Void> checkApi();

    ResponseEntity<Map<String, Object>> findCourts(int minNum, int maxNum);
}

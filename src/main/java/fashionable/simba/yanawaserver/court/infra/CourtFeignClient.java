package fashionable.simba.yanawaserver.court.infra;

import fashionable.simba.yanawaserver.court.application.CourtFeignApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
@FeignClient(name = "court-api", url = "http://openapi.seoul.go.kr:8088")
public interface CourtFeignClient extends CourtFeignApi {

    @Override
    @GetMapping("/6663504c7867656f3635516a6b4770/json/ListPublicReservationSport/1/1/%ED%85%8C%EB%8B%88%EC%8A%A4%EC%9E%A5")
    ResponseEntity<Void> checkApi();

    @Override
    @GetMapping("/6663504c7867656f3635516a6b4770/json/ListPublicReservationSport/{minNum}/{maxNum}/%ED%85%8C%EB%8B%88%EC%8A%A4%EC%9E%A5")
    ResponseEntity<Map<String, Object>> findCourts(@PathVariable("minNum") int minNum, @PathVariable("maxNum") int maxNum);
}

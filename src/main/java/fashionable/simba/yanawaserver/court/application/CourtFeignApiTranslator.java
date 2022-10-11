package fashionable.simba.yanawaserver.court.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.court.domain.Court;
import fashionable.simba.yanawaserver.court.infra.ApiResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CourtFeignApiTranslator {
    private static final String LIST_PUBLIC_RESERVATION_SPORT = "ListPublicReservationSport";
    private static final Logger log = LoggerFactory.getLogger(CourtFeignApiTranslator.class);

    private static final String ROW = "row";
    private final ObjectMapper objectMapper;

    public CourtFeignApiTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean isStatusOk(ResponseEntity<Void> response) {
        log.info("Check api CourtFeignClient");
        return HttpStatus.OK == response.getStatusCode();
    }

    public List<Court> getCourts(ResponseEntity<Map<String, Object>> response) {
        log.info("Find api using CourtFeignClient");
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> objectMap = (Map<String, Object>) Objects.requireNonNull(response.getBody()).get(LIST_PUBLIC_RESERVATION_SPORT);
            JSONObject jsonObject = new JSONObject(objectMap);
            List<ApiResponse> apiResponses = objectMapper.readValue(
                jsonObject.getJSONArray(ROW).toString(),
                new TypeReference<>() {
                }
            );
            return apiResponses.stream().distinct().map(apiResponse -> new Court(null, apiResponse.getAreaName(), apiResponse.getPlaceName(), apiResponse.getImagePath())).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn("Failed to find list, message is {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}

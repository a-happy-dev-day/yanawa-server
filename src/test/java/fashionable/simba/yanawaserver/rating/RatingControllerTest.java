package fashionable.simba.yanawaserver.rating;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatingControllerTest {

    @LocalServerPort
    private int RANDOM_PORT;

    @BeforeEach
    void setUp() {
        RestAssured.port = RANDOM_PORT;
    }

    @Test
    void 사용자가_참여자의_능력을_평가한다() throws JsonProcessingException {

        Map<String, String> param = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        String ratingScoreString = mapper.writeValueAsString(new RatingScore(BigDecimal.valueOf(3.0)));

        param.put("id", "1");
        param.put("participantId", "1");
        param.put("recruitmentId", "1");
        param.put("ratingScore", ratingScoreString);
        param.put("mannerTemperature", MannerTemperatureType.EXCELLENT.name());
        param.put("userId", "2");
        param.put("detail", "후기");

        ExtractableResponse<Response> response = RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(param)
            .post("rating")
            .then().log().all()
            .extract();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
package fashionable.simba.yanawaserver.review;

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
    void 사용자가_참여자의_능력을_평가한다() {

        Map<String, String> param = new HashMap<>();

        param.put("id", "1L");
        param.put("participantId", "1L");
        param.put("recruitmentId", "1L");
        param.put("ratingScore", String.valueOf(new RatingScore(BigDecimal.valueOf(3.0))));
        param.put("mannerTemperature", String.valueOf(MannerTemperatureType.EXCELLENT));
        param.put("userId", "2L");
        param.put("detail", "후기");

        ExtractableResponse<Response> response = RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(param)
            .post("/rating")
            .then().log().all()
            .extract();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
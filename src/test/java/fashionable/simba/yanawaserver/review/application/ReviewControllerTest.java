package fashionable.simba.yanawaserver.review.application;

import fashionable.simba.yanawaserver.review.domain.MannerTemperatureType;
import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.domain.RatingScore;
import fashionable.simba.yanawaserver.review.dto.ReviewRequest;
import fashionable.simba.yanawaserver.review.service.ReviewService;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {
    @MockBean
    ReviewService reviewService;

    @LocalServerPort
    private int RANDOM_PORT;

    ReviewRequest request = new ReviewRequest(1L, 1L, 1L, BigDecimal.valueOf(3.0), MannerTemperatureType.EXCELLENT, 2L, "후기");
    Review review = new Review(1L, 1L, 1L, new RatingScore(BigDecimal.valueOf(3.0)), MannerTemperatureType.EXCELLENT, 2L, "후기");

    @BeforeEach
    void setUp() {
        Mockito.when(reviewService.createReview(request)).thenReturn(review);
        RestAssured.port = RANDOM_PORT;
    }

    @Test
    void 사용자가_참여자의_능력을_평가한다() {

        Map<String, String> param = new HashMap<>();

        param.put("id", "1");
        param.put("participantId", "1");
        param.put("recruitmentId", "1");
        param.put("ratingScore", "3.0");
        param.put("mannerTemperatureType", MannerTemperatureType.EXCELLENT.name());
        param.put("userId", "2");
        param.put("detail", "후기");

        ExtractableResponse<Response> response = RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(param)
            .post("review")
            .then().log().all()
            .extract();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}

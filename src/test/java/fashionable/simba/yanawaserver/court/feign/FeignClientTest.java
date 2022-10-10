package fashionable.simba.yanawaserver.court.feign;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class FeignClientTest {

    @Test
    @DisplayName("데이터를 가져온다")
    void test1() {
        ExtractableResponse<Response> 데이터_조회 = RestAssured
            .given().contentType("application/json; charset=utf-8")
            .when().get("http://openapi.seoul.go.kr:8088/6663504c7867656f3635516a6b4770/json/ListPublicReservationSport/1/1000/%ED%85%8C%EB%8B%88%EC%8A%A4%EC%9E%A5")
            .then().extract();

        assertThat(데이터_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(데이터_조회.body().jsonPath().getList("ListPublicReservationSport.row.PLACENM").size())
            .isEqualTo(데이터_조회.body().jsonPath().getInt("ListPublicReservationSport.list_total_count"));
    }
}

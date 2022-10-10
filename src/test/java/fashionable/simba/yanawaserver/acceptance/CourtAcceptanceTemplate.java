package fashionable.simba.yanawaserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class CourtAcceptanceTemplate {

    public static ExtractableResponse<Response> 데이터_저장() {
        return RestAssured.given().log().all()
            .when().post("/v1/api/courts")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 데이터_조회(String name) {
        return RestAssured.given().log().all()
            .param("name", name)
            .when().get("/v1/api/courts")
            .then().log().all().extract();
    }
}

package fashionable.simba.yanawaserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MemberSteps {

    public static ExtractableResponse<Response> 로그인_요청(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login/token")
            .then().log().all()
            .statusCode(HttpStatus.OK.value()).extract();
    }

    public static String 로그인_되어_있음(String username) {
        ExtractableResponse<Response> response = 로그인_요청(username);
        return response.jsonPath().getString("accessToken");
    }

    public static ExtractableResponse<Response> 회원_목록_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 정보_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members/me")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    public static ExtractableResponse<Response> 로그아웃_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .when().get("/logout")
            .then().log().all().extract();
    }
}

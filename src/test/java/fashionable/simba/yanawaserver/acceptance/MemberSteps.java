package fashionable.simba.yanawaserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MemberSteps {


    public static String 로그인_코드_발급(String username, String password) {
        return 로그인_발급_요청(username, password).jsonPath().getString("accessCode");
    }

    public static ExtractableResponse<Response> 로그인_요청(String token) {
        Map<String, String> params = new HashMap<>();
        params.put("accessCode", token);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login/token")
            .then().log().all()
            .statusCode(HttpStatus.OK.value()).extract();
    }

    public static String 로그인_되어_있음(String username) {
        String token = 로그인_코드_발급(username, "password-admin");
        ExtractableResponse<Response> response = 로그인_요청(token);
        return response.jsonPath().getString("accessToken");
    }

    public static ExtractableResponse<Response> 로그인_발급_요청(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login")
            .then().log().all()
            .extract();
        return response;
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

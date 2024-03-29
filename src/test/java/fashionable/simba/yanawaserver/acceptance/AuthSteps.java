package fashionable.simba.yanawaserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class AuthSteps {
    public static final String PASSWORD_ADMIN = "password-admin";

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
            .extract();
    }

    public static String 로그인_되어_있음(String username) {
        String token = 로그인_코드_발급(username, PASSWORD_ADMIN);
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
            .when().post("/members")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 정보_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members/me")
            .then().log().all()
            .extract();
    }


    public static ExtractableResponse<Response> 미인증_사용자_정보_조회_요청() {
        return RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members/me")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 코드_재갱신_요청(String refreshToken) {
        Map<String, String> params = new HashMap<>();
        params.put("refreshToken", refreshToken);

        return RestAssured.given().log().all()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("token/refresh")
            .then().log().all()
            .extract();
    }


    public static ExtractableResponse<Response> 액세스_토큰_만료_요청(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("accessToken", accessToken);

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/token/expire/access")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 리프레시_토큰_만료_요청(String refreshToken, String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("refreshToken", refreshToken);

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/token/expire/refresh")
            .then().log().all()
            .extract();
    }
}

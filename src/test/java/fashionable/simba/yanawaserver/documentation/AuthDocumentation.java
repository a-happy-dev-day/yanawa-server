package fashionable.simba.yanawaserver.documentation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static fashionable.simba.yanawaserver.acceptance.MemberSteps.PASSWORD_ADMIN;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class AuthDocumentation extends Documentation {

    @Test
    void redirectKakaoLoginPage() {
        givenNotOauth()
            .filter(document("member/kakao",
                preprocessRequest(prettyPrint())))
            .when().get("/kakao/login")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void getAccessCode() {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", PASSWORD_ADMIN);

        givenNotOauth()
            .filter(document("member/login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void getAccessTokenAndRefreshToken() {
        Map<String, String> params = new HashMap<>();
        params.put("accessCode", accessCode);

        givenNotOauth()
            .filter(document("member/token",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login/token")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void refreshToken() {
        Map<String, String> params = new HashMap<>();
        params.put("refreshToken", refreshToken);

        givenNotOauth()
            .filter(document("member/refresh",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/refresh")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void logout() {
        givenOauth()
            .filter(document("member/logout",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/logout")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}

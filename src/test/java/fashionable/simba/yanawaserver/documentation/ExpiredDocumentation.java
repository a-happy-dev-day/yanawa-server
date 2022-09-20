package fashionable.simba.yanawaserver.documentation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class ExpiredDocumentation extends Documentation {
    @Test
    void refreshToken() {
        Map<String, String> params = new HashMap<>();
        params.put("refreshToken", refreshToken);

        givenNotOauth()
            .filter(document("token/refresh",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("token/refresh")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void accessToken_expired() {
        Map<String, String> params = new HashMap<>();
        params.put("accessToken", accessToken);

        givenOauth()
            .filter(document("expired/access",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/token/expire/access")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void refreshToken_expired() {
        Map<String, String> params = new HashMap<>();
        params.put("refreshToken", refreshToken);

        givenOauth()
            .filter(document("expired/refresh",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/token/expire/refresh")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}

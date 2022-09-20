package fashionable.simba.yanawaserver.documentation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class ExpiredDocumentation extends Documentation {
    @Test
    void accessToken_expired() {
        givenOauth()
            .filter(document("expired/access",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/token/access/expired")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void logout() {
        givenOauth()
            .filter(document("expired/refresh",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/token/refresh/expired")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}

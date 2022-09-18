package fashionable.simba.yanawaserver.documentation;


import fashionable.simba.yanawaserver.members.DataLoader;
import fashionable.simba.yanawaserver.utils.DatabaseCleanup;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;

import static fashionable.simba.yanawaserver.acceptance.MemberSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.로그인_요청;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.로그인_코드_발급;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class Documentation {
    @LocalServerPort
    int port;
    @Autowired
    private DatabaseCleanup databaseCleanup;
    @Autowired
    private DataLoader dataLoader;
    protected String username = "1";
    protected String accessCode;
    protected String accessToken;
    protected String refreshToken;
    protected RequestSpecification spec;

    protected RequestSpecification givenOauth() {
        return RestAssured.given(spec).log().all().auth().oauth2(accessToken);
    }

    protected RequestSpecification givenNotOauth() {
        return RestAssured.given(spec).log().all();
    }

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;

        databaseCleanup.execute();
        username = String.valueOf(dataLoader.loadData().get("user"));

        accessCode = 로그인_코드_발급(username, PASSWORD_ADMIN);
        ExtractableResponse<Response> response = 로그인_요청(accessCode);

        accessToken = response.jsonPath().getString("accessToken");
        refreshToken = response.jsonPath().getString("refreshToken");

        this.spec = new RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation))
            .build();
    }
}


package fashionable.simba.yanawaserver.documentation;


import fashionable.simba.yanawaserver.acceptance.MemberSteps;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class Documentation {
    public static final String USERNAME = "1";
    @LocalServerPort
    int port;
    protected String accessToken;

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

        accessToken = MemberSteps.로그인_되어_있음(USERNAME);

        this.spec = new RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation))
            .build();
    }
}


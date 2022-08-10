package fashionable.simba.yanawaserver.member.documentation;

import fashionable.simba.yanawaserver.members.domain.AccessToken;
import fashionable.simba.yanawaserver.members.service.MemberService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class MemberDocumentation extends Documentation {

    @MockBean
    MemberService memberService;

    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoidGhpcy1pcy1zcGVhciIsImV" +
        "tYWlsIjoicmpzY2tkZDEyQGdtYWlsLmNvbSJ9.S6wLnfUOvhU0Wx4kmNMiCAYS4rIdUyVI4FpGAjydfMM";

    @Test
    void login() {
        when(memberService.login()).thenReturn(new AccessToken(token));

        RestAssured
            .given(spec).log().all()
            .filter(document("member/login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/login")
            .then().log().all()
            .extract();
    }

    @Test
    void logout() {
        doNothing().when(memberService).logout();

        RestAssured
            .given(spec).log().all()
            .filter(document("member/logout",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/logout")
            .then().log().all()
            .extract();
    }
}

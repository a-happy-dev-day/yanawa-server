package fashionable.simba.yanawaserver.member.documentation;

import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class MemberDocumentation extends Documentation {

    @MockBean
    MemberService memberService;

    @Test
    void members_me() {
        when(memberService.findMemberByUserName(any()))
            .thenReturn(Optional.of(new Member("member@email.com", List.of(RoleType.ROLE_MEMBER.name()))));

        givenOauth()
            .filter(document("member/me",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/members/me")
            .then().log().all()
            .extract();

    }

    @Test
    void login() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "user@email.com");

        givenNotOauth()
            .filter(document("member/login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("/login/token")
            .then().log().all()
            .statusCode(HttpStatus.OK.value()).extract();
    }

    @Test
    void logout() {
        doNothing().when(memberService).logout();

        givenOauth()
            .filter(document("member/logout",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/logout")
            .then().log().all()
            .extract();
    }
}

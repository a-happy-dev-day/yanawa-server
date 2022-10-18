package fashionable.simba.yanawaserver.documentation;

import fashionable.simba.yanawaserver.members.domain.DefaultMember;
import fashionable.simba.yanawaserver.members.domain.MemberSex;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fashionable.simba.yanawaserver.acceptance.AuthSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_코드_발급;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class MemberDocumentation extends Documentation {

    @MockBean
    MemberService memberService;

    /**
     * 사용자의 정보를 조회한다.
     */
    @Test
    void get_members_me() {
        when(memberService.findMemberByUserName(any()))
            .thenReturn(Optional.of(
                new DefaultMember("nickname", 1L, "member@email.com", List.of(RoleType.ROLE_MEMBER.name()), false)
            ));

        givenOauth()
            .filter(document("member/me/get",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/members/me")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    /**
     * 사용자의 정보를 추가한다.
     */
    @Test
    void post_members_me() {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", "tis");
        params.put("birthDate", "1996-09-01");
        params.put("level", "0.5");
        params.put("sex", "MALE");

        givenNotOauth()
            .auth().oauth2(
                로그인_요청(로그인_코드_발급(String.valueOf(getData().get("firstVisitUser")), PASSWORD_ADMIN))
                    .jsonPath().getString("accessToken")
            )
            .filter(document("member/me/post",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/members/me")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}

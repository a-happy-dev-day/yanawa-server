package fashionable.simba.yanawaserver.documentation;

import fashionable.simba.yanawaserver.members.domain.DefaultMember;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import fashionable.simba.yanawaserver.members.service.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fashionable.simba.yanawaserver.acceptance.MemberSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.로그인_코드_발급;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class MemberDocumentation extends Documentation {

    @MockBean
    MemberService memberService;

    @Test
    void members_me() {
        when(memberService.findMemberByUserName(any()))
            .thenReturn(Optional.of(
                new DefaultMember(1L, "member@email.com", List.of(RoleType.ROLE_MEMBER.name()))
            ));

        givenOauth()
            .filter(document("member/me",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/members/me")
            .then().log().all()
            .extract();
    }

}

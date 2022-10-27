package fashionable.simba.yanawaserver.documentation;

import fashionable.simba.yanawaserver.kakao.service.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.kakao.infra.dto.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class KakaoLoginDocumentation extends Documentation {
    private static final KakaoAccessToken ACCESS_TOKEN = new KakaoAccessToken("Bearer", "accessToken", "refreshToken");
    private static final KakaoMember KAKAO_MEMBER = new KakaoMember(1L, "tis@email.com", "tis", "tis.img", "tis.jpg", false);
    @MockBean
    KakaoAuthenticationService kakaoAuthenticationService;

    @Test
    void getKakaoLoginPage() {
        when(kakaoAuthenticationService.getLoginUri())
            .thenReturn("https://kauth.kakao.com/oauth/authorize");

        givenNotOauth()
            .filter(document("page/kakao",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("login/kakao")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void kakaoLogin() {
        when(kakaoAuthenticationService.getAccessToken(anyString())).thenReturn(ACCESS_TOKEN);
        when(kakaoAuthenticationService.getUserInfo(ACCESS_TOKEN)).thenReturn(KAKAO_MEMBER);

        Map<String, String> params = new HashMap<>();
        params.put("accessCode", "insert kakao access code");

        givenNotOauth()
            .filter(document("login/kakao",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().post("login/kakao")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}

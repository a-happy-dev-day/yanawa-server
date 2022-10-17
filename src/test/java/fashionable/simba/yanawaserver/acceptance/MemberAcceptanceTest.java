package fashionable.simba.yanawaserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_되어_있음;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.정보_조회_요청;

public class MemberAcceptanceTest extends AcceptanceTest {

    /**
     * Given : 사용자가 회원가입을 하고
     * When : 로그인을 하면
     * When : 첫사용자인지 확인하고
     * Then : 첫사용자이면 자신의 정보를 작성한다.
     */
    @Test
    void visitFirstMember() {
        // given
        String id = getId("firstVisitUser");
        String token = 로그인_되어_있음(id);

        // when
        String nickname = "tis";
        String birthDate = "1996-09-01";
        String level = "0.5";

        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        Assertions.assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(정보_조회.jsonPath().getString("isFirst")).isEqualTo("true");

        // then
        ExtractableResponse<Response> 사용자_정보_등록 = 사용자_정보_등록(token, nickname, birthDate, level);
        Assertions.assertThat(사용자_정보_등록.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given : 사용자가 회원가입을 하고
     * When : 로그인을 하면
     * When : 첫사용자인지 확인하고
     * Then : 첫사용자이면서 닉네임이 저장되어 있으면 저장된 닉네임과 함께 자신의 정보를 작성한다.
     */
    @Test
    void visitFirstMember_existNickname() {

        // given
        String id = getId("firstVisitUser");
        String token = 로그인_되어_있음(id);

        // when
        String birthDate = "1996-09-01";
        String level = "0.5";

        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        Assertions.assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(정보_조회.jsonPath().getString("isFirst")).isEqualTo("true");

        // then
        String nickname = 정보_조회.jsonPath().getString("nickname");

        ExtractableResponse<Response> 사용자_정보_등록 = 사용자_정보_등록(token, nickname, birthDate, level);
        Assertions.assertThat(사용자_정보_등록.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    /**
     * Given : 사용자가 회원가입을 하고
     * When : 로그인을 하면
     * When : 처음 사용자인지 확인하고
     * Then : 처음 사용자가 아니면 랜딩 페이지로 이동한다.
     */
    @Test
    void visitNotFirstMember() {
        // given
        String id = getId("user");
        String token = 로그인_되어_있음(id);

        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        Assertions.assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(정보_조회.jsonPath().getString("isFirst")).isEqualTo("false");
    }


    private ExtractableResponse<Response> 사용자_정보_등록(String token, String nickname, String birthDate, String level) {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", nickname);
        params.put("birthDate", birthDate);
        params.put("level", level);

        return RestAssured.given().log().all()
            .auth().oauth2(token)
            .body(params)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/members/me")
            .then().log().all()
            .extract();
    }

}

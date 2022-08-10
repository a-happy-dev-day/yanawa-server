package fashionable.simba.yanawaserver.member.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.로그인_되어_있음;
import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.정보_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthAcceptanceTest extends AcceptanceTest {

    /**
     * Given 사용자가 로그인하면
     * When 토큰을 발급받고
     * Then 토큰으로 정보를 조회할 수 있습니다.
     */
    @Test
    void members_me_get_user_email() {
        // given
        String email = "admin@email.com";

        // when
        String token = 로그인_되어_있음(email);
        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        // then
        assertThat(정보_조회.jsonPath().getString("email")).isEqualTo(email);
    }

    /**
     * When 로그인하지 않고 정보를 조회하면
     * Then 401 Unauthorized 예외가 발생한다.
     */
    @Test
    void members_me_unauthorized() {
        // when
        ExtractableResponse<Response> 정보_조회 = RestAssured.given().log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members/me")
            .then().log().all()
            .extract();

        // then
        assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * When 유효시간이 지난 토큰을 사용해 조회하면
     * Then 401 Unauthorized 예외가 발생한다.
     */
    @Test
    void members_me_expiredTime() {
        // given
        String accessToken = "eyJhbGciOiJIUzI1NiJ9" +
            ".eyJzdWIiOiJhZG1pbkBlbWFpbC5jb20iLCJpYXQiOjE2NjAxMzM1MzQsImV4cCI6MTY2MDEzMzUzNCwicm9sZXMiOlsiUk9MRV9BRE1JTiJdfQ" +
            ".W0ZKRaSSoLFpvsPCYAVfXOWtTjxt7HWcgMIaDenT2W4";

        // when
        ExtractableResponse<Response> 정보_조회 = RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/members/me")
            .then().log().all()
            .extract();

        // then
        assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}

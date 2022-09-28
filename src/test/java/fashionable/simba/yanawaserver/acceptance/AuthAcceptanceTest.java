package fashionable.simba.yanawaserver.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static fashionable.simba.yanawaserver.acceptance.AuthSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_되어_있음;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_발급_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_코드_발급;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.미인증_사용자_정보_조회_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.정보_조회_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.회원_목록_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

class AuthAcceptanceTest extends AcceptanceTest {

    /**
     * When 유효하지 않은 액세스 코드로 접근하면
     * Then 401 예외가 발생합니다.
     */
    @Test
    void use_invalid_accessCode() {
        ExtractableResponse<Response> response = 로그인_요청("invalid access code");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * When 로그인에 성공하면
     * Then Access Token 과 Refresh Token을 발급 받습니다.
     */
    @Test
    void login_success() {
        // given
        String id = getId("admin");

        String accessCode = 로그인_코드_발급(id, PASSWORD_ADMIN);
        ExtractableResponse<Response> response = 로그인_요청(accessCode);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("accessToken")).isNotNull();
        assertThat(response.jsonPath().getString("refreshToken")).isNotNull();
    }

    /**
     * Given 사용자가 로그인하면
     * When 토큰을 발급받고
     * Then 토큰으로 정보를 조회할 수 있습니다.
     */
    @Test
    void members_me_get_user_email() {
        // given
        String email = "admin@email.com";
        String id = getId("admin");

        // when
        String token = 로그인_되어_있음(id);
        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        // then
        assertThat(정보_조회.jsonPath().getString("email")).isEqualTo(email);
    }


    /**
     * Given 관리자가 로그인해서
     * When 사용자 목록을 조회하면
     * Then 목록을 조회할 수 있습니다.
     */
    @Test
    void get_members() {
        // given
        String id = getId("admin");

        // when
        String token = 로그인_되어_있음(id);
        ExtractableResponse<Response> 회원_목록_조회 = 회원_목록_조회_요청(token);

        // then
        assertThat(회원_목록_조회.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 일반 사용자가 로그인해서
     * When 사용자 목록을 조회하면
     * Then 권한이 맞지 않아 401 예외가 발생합니다.
     */
    @Test
    void get_members_invalid_authorization() {
        // given
        String id = getId("member");
        // when
        String token = 로그인_되어_있음(id);
        ExtractableResponse<Response> 회원_목록_조회 = 회원_목록_조회_요청(token);

        // then
        assertThat(회원_목록_조회.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }


    /**
     * When 로그인하지 않고 정보를 조회하면
     * Then 401 Unauthorized 예외가 발생한다.
     */
    @Test
    void members_me_unauthorized() {
        // when
        ExtractableResponse<Response> 정보_조회 = 미인증_사용자_정보_조회_요청();

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
            ".eyJzdWIiOiI3IiwiaWF0IjoxNjYzMTM1NDkzLCJleHAiOjE2NjMxMzkwOTMsInJvbGVzIjpbIlJPTEVfTUVNQkVSIl19" +
            ".jBTEKPOxjgO7-2IynGeVkoe9sqPLGG5sYNRSGrdOVbU";

        // when
        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(accessToken);

        // then
        assertThat(정보_조회.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }


    /**
     * When : 비밀번호를 모르는 사용자가 접근하면
     * Then : 401 예외가 발생한다.
     */
    @Test
    void login_failed_invalid_password() {
        String id = getId("admin");
        ExtractableResponse<Response> response = 로그인_발급_요청(id, "invalid-password");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }


    /**
     * When : 비밀번호를 모르는 사용자가 접근하면
     * Then : 401 예외가 발생한다.
     */
    @Test
    void login_failed_null_password() {
        String id = getId("admin");
        ExtractableResponse<Response> response = 로그인_발급_요청(id, null);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * When : username을 모르는 사용자가 접근하면
     * Then : 401 예외가 발생한다.
     */
    @Test
    void login_failed_invalid_username() {
        ExtractableResponse<Response> response = 로그인_발급_요청("12", "password-admin");
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}

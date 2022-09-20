package fashionable.simba.yanawaserver.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static fashionable.simba.yanawaserver.acceptance.AuthSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.로그인_코드_발급;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.리프레시_토큰_만료_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.액세스_토큰_만료_요청;
import static fashionable.simba.yanawaserver.acceptance.AuthSteps.코드_재갱신_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenAcceptanceTest extends AcceptanceTest {

    /**
     * When Refresh Token을 입력하면 Access Code을 발급 받고
     * Then Access Code로 Access Token과 Refresh Token을 재발급 받고
     * Then Access Token으로 사용자 정보를 조회한다.
     */
    @Test
    void refresh_token() {
        // given
        String id = getId("admin");
        String accessCode = 로그인_코드_발급(id, PASSWORD_ADMIN);
        String refreshToken = 로그인_요청(accessCode).jsonPath().getString("refreshToken");

        // when
        ExtractableResponse<Response> response = 코드_재갱신_요청(refreshToken);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("accessToken")).isNotNull();
    }

    /**
     * When 유효하지 않은 Refresh Token을 입력하면
     * Then 401 예외가 발생한다.
     */
    @Test
    void refresh_token_failed() {
        // when
        ExtractableResponse<Response> response = 코드_재갱신_요청("invalid refreshToken");

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * Given 사용자는 로그인하먄 토큰을 받고
     * When 리프레시 토큰과 액세스 토큰을 입력해 로그아웃 요청을 하면
     * Then 로그아웃에 성공한다.
     */
    @Test
    void logout() {
        // given
        String id = getId("admin");
        String accessCode = 로그인_코드_발급(id, PASSWORD_ADMIN);
        ExtractableResponse<Response> response = 로그인_요청(accessCode);
        String accessToken = response.jsonPath().getString("accessToken");
        String refreshToken = response.jsonPath().getString("refreshToken");

        // when
        ExtractableResponse<Response> 리프레시_토큰_요청 = 리프레시_토큰_만료_요청(refreshToken, accessToken);
        ExtractableResponse<Response> 액세스_토큰_만료_요청 = 액세스_토큰_만료_요청(accessToken);

        // then
        assertThat(리프레시_토큰_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(액세스_토큰_만료_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    /**
     * Given 사용자가 액세스 토큰을 입력해
     * When 액세스 토큰 만려 요청하면
     * Then 액세스 토큰이 만료된다.
     */
    @Test
    void expireAccessToken() {
        // given
        String id = getId("admin");
        String accessCode = 로그인_코드_발급(id, PASSWORD_ADMIN);
        ExtractableResponse<Response> response = 로그인_요청(accessCode);
        String accessToken = response.jsonPath().getString("accessToken");

        // when
        ExtractableResponse<Response> 액세스_토큰_만료_요청 = 액세스_토큰_만료_요청(accessToken);

        // then
        assertThat(액세스_토큰_만료_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    /**
     * Given 사용자가 리프레시 토큰을 입력하면
     * When 리프레시 토큰 만려 요청하면
     * Then 리프레시 토큰이 만료된다.
     */
    @Test
    void expireRefreshToken() {
        // given
        String id = getId("admin");
        String accessCode = 로그인_코드_발급(id, PASSWORD_ADMIN);
        ExtractableResponse<Response> response = 로그인_요청(accessCode);
        String accessToken = response.jsonPath().getString("accessToken");
        String refreshToken = response.jsonPath().getString("refreshToken");

        // when
        ExtractableResponse<Response> 리프레시_토큰_요청 = 리프레시_토큰_만료_요청(refreshToken, accessToken);

        // then
        assertThat(리프레시_토큰_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}

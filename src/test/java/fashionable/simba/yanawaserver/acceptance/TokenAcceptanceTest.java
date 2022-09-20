package fashionable.simba.yanawaserver.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static fashionable.simba.yanawaserver.acceptance.MemberSteps.PASSWORD_ADMIN;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.로그인_요청;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.로그인_코드_발급;
import static fashionable.simba.yanawaserver.acceptance.MemberSteps.코드_재갱신_요청;
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

        ExtractableResponse<Response> response = 코드_재갱신_요청(refreshToken);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("accessToken")).isNotNull();
    }

    /**
     * When 유효하지 않은 Refresh Token을 입력하면
     * Then 401 예외가 발생한다.
     */
    @Test
    void refresh_token_failed() {
        ExtractableResponse<Response> response = 코드_재갱신_요청("invalid refreshToken");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}

package fashionable.simba.yanawaserver.member.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.로그인_되어_있음;
import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.정보_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthAcceptanceTest {

    /**
     * Given 사용자가 로그인하면
     * When 토큰을 발급받고
     * Then 토큰으로 정보를 조회할 수 있습니다.
     */
    @Test
    void get_user_info() {
        // given
        String email = "admin@email.com";

        // when
        String token = 로그인_되어_있음(email);
        ExtractableResponse<Response> 정보_조회 = 정보_조회_요청(token);

        // then
        assertThat(정보_조회.jsonPath().getString("id")).isNotNull();
        assertThat(정보_조회.jsonPath().getString("email")).isEqualTo(email);
    }

}

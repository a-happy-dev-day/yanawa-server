package fashionable.simba.yanawaserver.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class MemberAcceptanceTest extends AcceptanceTest {


    /**
     * Given : 기존 회원가입이 된 사용자가 로그인하면
     * Then : 토큰을 발급받습니다.
     */
    @Test
    void login() {
        String 기존_사용자 = "1";

        ExtractableResponse<Response> 로그인 = MemberSteps.로그인_요청(기존_사용자);

        Assertions.assertThat(로그인.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given : 로그인 한 사용자가
     * When : 로그아웃을 진행하면
     * Then : 로그아웃이 완료되고 토큰이 만료됩니다.
     */
    @Test
    @Disabled
    void logout() {
        String 기존_사용자 = "1";

        String accessToken = MemberSteps.로그인_되어_있음(기존_사용자);

        ExtractableResponse<Response> 로그아웃 = MemberSteps.로그아웃_요청(accessToken);
        Assertions.assertThat(로그아웃.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}

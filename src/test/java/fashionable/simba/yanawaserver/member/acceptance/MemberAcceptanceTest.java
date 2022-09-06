package fashionable.simba.yanawaserver.member.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.로그아웃_요청;
import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.로그인_되어_있음;
import static fashionable.simba.yanawaserver.member.acceptance.MemberSteps.로그인_요청;

class MemberAcceptanceTest extends AcceptanceTest {

    /**
     * Given : 처음 사용하는 사용자가 로그인하면
     * When : 회원가입을 진행되고 로그인이 됩니다.
     * Then : 로그인이 완료되면 토큰을 발급받습니다.
     */
    @Test
    void join_and_login() {
        String 새로운_사용자 = "new@email.com";

        ExtractableResponse<Response> 로그인 = 로그인_요청(새로운_사용자);

        Assertions.assertThat(로그인.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    /**
     * Given : 기존 회원가입이 된 사용자가 로그인하면
     * Then : 토큰을 발급받습니다.
     */
    @Test
    void login() {
        String 기존_사용자 = "admin@email.com";

        ExtractableResponse<Response> 로그인 = 로그인_요청(기존_사용자);

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
        String 기존_사용자 = "admin@email.com";

        String accessToken = 로그인_되어_있음(기존_사용자);

        ExtractableResponse<Response> 로그아웃 = 로그아웃_요청(accessToken);
        Assertions.assertThat(로그아웃.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
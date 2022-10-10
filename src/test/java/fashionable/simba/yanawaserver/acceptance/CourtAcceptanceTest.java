package fashionable.simba.yanawaserver.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static fashionable.simba.yanawaserver.acceptance.CourtAcceptanceTemplate.데이터_저장;
import static fashionable.simba.yanawaserver.acceptance.CourtAcceptanceTemplate.데이터_조회;
import static org.assertj.core.api.Assertions.assertThat;

class CourtAcceptanceTest extends AcceptanceTest {

    private static final String 성동구 = "성동구";
    private static final String 응봉공원 = "응봉공원";
    private static final String 성동구_응봉공원 = "성동구 응봉공원";
    private static final String NAME = "name";

    /**
     * Given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
     * When & Then 이름을 입력해 데이터를 조회합니다.
     */
    @Test
    @DisplayName("이름을 입력해 코트장을 검색한 후 데이터를 조회합니다.")
    void test2() {
        // given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
        ExtractableResponse<Response> 데이터_저장 = 데이터_저장();
        assertThat(데이터_저장.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // when & then 이름을 입력해 데이터를 조회합니다.
        ExtractableResponse<Response> 이름_조회 = 데이터_조회(응봉공원);
        assertThat(이름_조회.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(코트_정보_목록_조회(이름_조회, NAME)).contains(성동구_응봉공원);
    }

    /**
     * Given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
     * When & Then 지역을 입력해 데이터를 조회합니다.
     */
    @Test
    @DisplayName("지역을 입력해 검색한 후 데이터를 조회합니다.")
    void test3() {
        // given
        ExtractableResponse<Response> 데이터_저장 = 데이터_저장();
        assertThat(데이터_저장.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // when & then 지역을 입력해 데이터를 조회합니다.
        ExtractableResponse<Response> 지역_조회 = 데이터_조회(성동구);
        assertThat(지역_조회.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(코트_정보_목록_조회(지역_조회, NAME)).contains(성동구_응봉공원);
    }

    /**
     * Given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
     * When & Then 정확한 지역과 이름을 입력해 데이터를 조회합니다.
     */
    @Test
    @DisplayName("정확한 지역과 이름을 입력해 데이터를 조회합니다.")
    void test4() {
        // given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
        ExtractableResponse<Response> 데이터_저장 = 데이터_저장();
        assertThat(데이터_저장.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // when & then 정확한 지역과 이름을 입력해 데이터를 조회합니다.
        ExtractableResponse<Response> 지역_이름_조회 = 데이터_조회(성동구_응봉공원);
        assertThat(지역_이름_조회.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(코트_정보_목록_조회(지역_이름_조회, NAME)).contains(성동구_응봉공원);
    }

    /**
     * Given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
     * When & Then 서울시가 아닌 지역의 이름을 검색하면 데이터가 조회되지 않습니다.
     */
    @Test
    @DisplayName("서울시가 아닌 지역의 이름을 검색하면 데이터가 조회되지 않습니다.")
    void test5() {
        // given 공공 데이터 API에서 데이터 목록을 조회해 저장하고
        ExtractableResponse<Response> 데이터_저장 = 데이터_저장();
        assertThat(데이터_저장.statusCode()).isEqualTo(HttpStatus.CREATED.value());

        // when & then 서울시가 아닌 지역의 이름을 검색하면 데이터가 조회되지 않습니다.
        ExtractableResponse<Response> 데이터_조회 = 데이터_조회(성동구_응봉공원);
        assertThat(데이터_조회.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    private List<String> 코트_정보_목록_조회(ExtractableResponse<Response> 데이터_조회, String name) {
        return 데이터_조회.jsonPath().getList(name);
    }

}

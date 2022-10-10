package fashionable.simba.yanawaserver.court.infra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApiResponseTest {
    private static final String 응봉공원 = "응봉공원";

    private static final String 성동구 = "성동구";
    private static final ApiResponse 다른_성동구_응봉공원 = new ApiResponse(성동구, 응봉공원, "https://abc");
    private static final ApiResponse 성동구_응봉공원 = new ApiResponse(성동구, 응봉공원, "https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=16363549760937EF7S2IE9XI7VHOKWMHLIAMJK");

    @Test
    @DisplayName("ApiResponse 생성")
    void test1() {
        assertDoesNotThrow(
            () -> new ApiResponse("성동구", "응봉공원", "https://yeyak.seoul.go.kr/web/common/file/FileDown.do?file_id=16363549760937EF7S2IE9XI7VHOKWMHLIAMJK")
        );
    }

    @Test
    @DisplayName("placeName과 AreaName이 같다면 True이다.")
    void test3() {
        // when & then
        assertThat(성동구_응봉공원).isEqualTo(다른_성동구_응봉공원);
    }


    @Test
    @DisplayName("클래스가 다르면 다른 객체이다.")
    void test4() {
        // given
        ApiResponse 성동구_응봉공원 = new ApiResponse(성동구, 응봉공원, "경로");
        Object otherClass = new Object();

        // when & then
        assertThat(성동구_응봉공원).isNotEqualTo(otherClass);
    }

    @Test
    @DisplayName("AreaName이 같지 않다면 다른 객체이다.")
    void test5() {
        // given
        String 다른공원 = "다른공원";
        ApiResponse 다른_공원 = new ApiResponse(성동구, 다른공원, "이미지 경로");
        // when & then
        assertThat(다른_공원).isNotEqualTo(성동구_응봉공원);
    }

    @Test
    @DisplayName("placeName이 같지 않다면 다른 객체이다.")
    void test6() {
        // given
        String 다른구 = "다른구";
        ApiResponse 다른_공원 = new ApiResponse(다른구, 응봉공원, "이미지 경로");
        // when & then
        assertThat(다른_공원).isNotEqualTo(성동구_응봉공원);
    }

    @Test
    @DisplayName("null과는 다른 객체이다.")
    void test7() {
        // when & then
        assertThat(성동구_응봉공원).isNotEqualTo(null);
    }
}

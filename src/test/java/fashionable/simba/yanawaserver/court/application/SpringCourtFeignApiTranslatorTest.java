package fashionable.simba.yanawaserver.court.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SpringCourtFeignApiTranslatorTest {


    private CourtFeignApiTranslator courtFeignApiTranslator;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        courtFeignApiTranslator = new CourtFeignApiTranslator(objectMapper);
    }

    @Test
    @DisplayName("API 헬스 체크를 합니다.")
    void test1() {
        assertThat(courtFeignApiTranslator.isStatusOk(ResponseEntity.ok().build())).isTrue();
    }


    @Test
    @DisplayName("변경 도중 실패가 발생하면 IOException이 발생합니다")
    void test3() {
        Map<String, Object> map = new HashMap<>();
        Assertions.assertThatThrownBy(
            () -> courtFeignApiTranslator.getCourts(ResponseEntity.ok(map))
        ).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("API의 상태가 OK가 아니면 false를 리턴합니다.")
    void test4() {
        assertThat(courtFeignApiTranslator.isStatusOk(ResponseEntity.badRequest().build())).isFalse();
    }
}

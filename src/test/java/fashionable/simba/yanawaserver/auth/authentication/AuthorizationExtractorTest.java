package fashionable.simba.yanawaserver.auth.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorizationExtractorTest {
    public static final String AUTHORIZATION = "Authorization";

    MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("type이 다르면 extract를 하지 않는다.")
    void extract_invalid_token() {
        request.addHeader(AUTHORIZATION, "NONE none.type");

        String actual = AuthorizationExtractor.extract(request, AuthorizationType.BEARER);
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("인증 정보가 많다면 그 중 앞 부분만 잘라서 사용한다.")
    void extract() {
        Map<String, String> headers = new HashMap<>();
        request.addHeader(AUTHORIZATION, "bearer auth,isAuth");

        String actual = AuthorizationExtractor.extract(request, AuthorizationType.BEARER);
        assertThat(actual).isEqualTo("auth");
    }
}

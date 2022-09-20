package fashionable.simba.yanawaserver.global.jwt.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

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
}

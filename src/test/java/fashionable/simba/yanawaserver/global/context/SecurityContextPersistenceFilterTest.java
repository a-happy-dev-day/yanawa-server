package fashionable.simba.yanawaserver.global.context;

import fashionable.simba.yanawaserver.global.context.SecurityContextHolder;
import fashionable.simba.yanawaserver.global.context.SecurityContextPersistenceFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SecurityContextPersistenceFilterTest {
    private static final String SPRING_SECURITY_CONTEXT_KEY = "SPRING_SECURITY_CONTEXT";
    private static final Object HANDLER = new Object();
    public static final String UNAUTHORIZED_CODE = "unauthorized code";

    @Autowired
    SecurityContextPersistenceFilter filter = new SecurityContextPersistenceFilter();
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Test
    @DisplayName("세션의 정보가 없다면 빈 정보를 가져온다.")
    void readSecurityContextFromSession_session_is_null() {
        // when
        filter.preHandle(request, response, HANDLER);

        // then
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("세션의 정보가 SecurityContext와 다른 정보라면 빈 정보를 가져온다.")
    void readSecurityContextFromSession_invalid_session_type() {
        // given
        MockHttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY, UNAUTHORIZED_CODE);

        // when
        Mockito.when(request.getSession()).thenReturn(httpSession);
        filter.preHandle(request, response, HANDLER);

        // then
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}

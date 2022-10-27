package fashionable.simba.yanawaserver.global.provider;

import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import fashionable.simba.yanawaserver.token.domain.TokenManager;
import fashionable.simba.yanawaserver.token.exception.InvalidTokenException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationTokenProviderTest {
    private AuthorizationTokenProvider authorizationTokenProvider;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private TokenManager tokenManager;

    @BeforeEach
    void setUp() {
        authorizationTokenProvider = new AuthorizationTokenProvider(jwtTokenProvider, tokenManager);
    }

    @Test
    @DisplayName("액세스 토큰을 가져와 principal과 roles를 가져온다.")
    void authenticate() {
        // given
        String principal = "member";
        List<String> roles = List.of(RoleType.ROLE_MEMBER.name());
        AuthenticationToken token = new AuthenticationToken("access token");

        // when
        doNothing().when(tokenManager).verifyAccessToken(token.getPrincipal());
        when(jwtTokenProvider.getPrincipal(token.getPrincipal())).thenReturn(principal);
        when(jwtTokenProvider.getRoles(token.getPrincipal())).thenReturn(roles);

        // then
        Authentication authentication = authorizationTokenProvider.authenticate(token);
        Assertions.assertThat(authentication.getPrincipal()).isEqualTo(principal);
        Assertions.assertThat(authentication.getAuthorities()).containsExactly(RoleType.ROLE_MEMBER.name());
    }

    @Test
    @DisplayName("유효하지 않은 액세스 토큰이면 AuthenticationException 예외가 발생한다.")
    void authenticate_logoutToken() {
        // given
        AuthenticationToken invalidToken = new AuthenticationToken("invalid access token");
        // when
        doThrow(InvalidTokenException.class).when(tokenManager).verifyAccessToken(invalidToken.getPrincipal());

        // then
        Assertions.assertThatThrownBy(
            () -> authorizationTokenProvider.authenticate(invalidToken)
        ).isInstanceOf(InvalidTokenException.class);
    }
}

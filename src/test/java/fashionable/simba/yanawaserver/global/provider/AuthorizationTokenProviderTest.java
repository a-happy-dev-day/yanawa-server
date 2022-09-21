package fashionable.simba.yanawaserver.global.provider;

import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.token.TokenDetailsService;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationTokenProviderTest {
    private AuthorizationTokenProvider authorizationTokenProvider;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private TokenDetailsService tokenDetailsService;

    @BeforeEach
    void setUp() {
        authorizationTokenProvider = new AuthorizationTokenProvider(jwtTokenProvider, tokenDetailsService);
    }

    @Test
    @DisplayName("액세스 토큰을 가져와 principal과 roles를 가져온다.")
    void authenticate() {
        // given
        String principal = "member";
        List<String> roles = List.of(RoleType.ROLE_MEMBER.name());
        AuthenticationToken token = new AuthenticationToken("access token");

        // when
        when(jwtTokenProvider.validateToken(token.getPrincipal())).thenReturn(true);
        when(tokenDetailsService.validateAccessToken(token.getPrincipal())).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(token.getPrincipal())).thenReturn(principal);
        when(jwtTokenProvider.getRoles(token.getPrincipal())).thenReturn(roles);

        // then
        Authentication authentication = authorizationTokenProvider.authenticate(token);
        Assertions.assertThat(authentication.getPrincipal()).isEqualTo(principal);
        Assertions.assertThat(authentication.getAuthorities()).containsExactly(RoleType.ROLE_MEMBER.name());
    }

    @Test
    @DisplayName("유효하지 않은 액세스 토큰이면 AuthenticationException 예외가 발생한다.")
    void authenticate_invalidToken() {
        // given
        AuthenticationToken invalidToken = new AuthenticationToken("invalid access token");

        // when
        when(jwtTokenProvider.validateToken(invalidToken.getPrincipal())).thenReturn(false);

        // then
        Assertions.assertThatThrownBy(
            () -> authorizationTokenProvider.authenticate(invalidToken)
        ).isInstanceOf(AuthenticationException.class);
    }

    @Test
    @DisplayName("로그아웃한 사용자의 토큰이면 AuthenticationException 예외가 발생한다.")
    void authenticate_logoutToken() {
        // given
        AuthenticationToken invalidToken = new AuthenticationToken("invalid access token");
        // when
        when(jwtTokenProvider.validateToken(invalidToken.getPrincipal())).thenReturn(true);
        when(tokenDetailsService.validateAccessToken(invalidToken.getPrincipal())).thenReturn(false);

        // then
        Assertions.assertThatThrownBy(
            () -> authorizationTokenProvider.authenticate(invalidToken)
        ).isInstanceOf(AuthenticationException.class);
    }
}

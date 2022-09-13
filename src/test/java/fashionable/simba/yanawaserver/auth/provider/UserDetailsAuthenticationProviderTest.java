package fashionable.simba.yanawaserver.auth.provider;

import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
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
class UserDetailsAuthenticationProviderTest {
    private static final String PRINCIPAL = "user@email.com";
    private static final AuthenticationToken AUTHENTICATION_TOKEN = new AuthenticationToken(PRINCIPAL);
    private UserDetailsAuthenticationProvider provider;

    @Mock
    private UserDetailsService service;

    @BeforeEach
    void setUp() {
        provider = new UserDetailsAuthenticationProvider(service);
    }

    @Test
    @DisplayName("정보를 가져와 정보를 조회한다.")
    void authenticate() {
        when(service.loadUserByUsername(PRINCIPAL))
            .thenReturn(new User(PRINCIPAL, List.of(RoleType.ROLE_ADMIN.name())));

        Authentication authenticate = provider.authenticate(AUTHENTICATION_TOKEN);
        Assertions.assertThat(authenticate.getPrincipal()).isEqualTo(PRINCIPAL);
    }

    @Test
    @DisplayName("정보가 없으면 예외가 발생한다.")
    void authenticate_userDetails_null() {
        when(service.loadUserByUsername(PRINCIPAL))
            .thenReturn(null);

        Assertions.assertThatThrownBy(
            () -> provider.authenticate(AUTHENTICATION_TOKEN)
        ).isInstanceOf(AuthenticationException.class);
    }
}

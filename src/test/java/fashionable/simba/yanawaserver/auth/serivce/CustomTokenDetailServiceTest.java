package fashionable.simba.yanawaserver.auth.serivce;

import fashionable.simba.yanawaserver.global.token.domain.ValidAccessTokenRepository;
import fashionable.simba.yanawaserver.global.token.domain.ValidRefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomTokenDetailServiceTest {

    CustomTokenDetailService customTokenDetailService;

    @Mock
    ValidAccessTokenRepository invalidAccessTokenRepository;

    @Mock
    ValidRefreshTokenRepository invalidRefreshTokenRepository;

    @BeforeEach
    void setUp() {
        customTokenDetailService = new CustomTokenDetailService(invalidAccessTokenRepository, invalidRefreshTokenRepository);
    }

    @Test
    @DisplayName("invalidAccessTokenRepository에 존재하는 토큰이면 false를 반환한다.")
    void validateAccessToken() {
        // given
        String accessToken = "invalid access token";

        // when
        when(invalidAccessTokenRepository.existsById(accessToken)).thenReturn(true);

        // then
        boolean actual = customTokenDetailService.validateAccessToken(accessToken);
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("invalidAccessTokenRepository에 존재하지 않는 토큰이면 false를 반환한다.")
    void validateAccessToken_validToken() {
        // given
        String accessToken = "access token";

        // when
        when(invalidAccessTokenRepository.existsById(accessToken)).thenReturn(false);

        // then
        boolean actual = customTokenDetailService.validateAccessToken(accessToken);
        assertThat(actual).isTrue();
    }
}

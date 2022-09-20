package fashionable.simba.yanawaserver.auth.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenManagementServiceTest {

    @Mock
    private InvalidAccessTokenRepository invalidAccessTokenRepository;
    @Mock
    private InvalidRefreshTokenRepository invalidRefreshTokenRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    private TokenManagementService tokenManagementService;

    @BeforeEach
    void setUp() {
        tokenManagementService = new TokenManagementService(invalidAccessTokenRepository, invalidRefreshTokenRepository, jwtTokenProvider);
    }

    @Test
    @DisplayName("유효한 액세스 토큰이 입력되고, 데이터베이스에 저장되어 있지 않다면 데이터베이스에 저장한다.")
    void expireAccessToken_saveRepository() {
        // given
        String accessToken = "accessCode";

        // when
        when(jwtTokenProvider.validateToken(accessToken)).thenReturn(true);
        when(invalidAccessTokenRepository.existsById(any())).thenReturn(false);

        // then
        tokenManagementService.expireAccessToken(accessToken);
        verify(invalidAccessTokenRepository, atLeast(1)).save(any());
    }

    @Test
    @DisplayName("액세스 토큰이 유효하지 않으면 데이터베이스에 저장하지 않는다.")
    void expireAccessToken_invalidToken() {
        // given
        String accessToken = "accessCode";

        // when
        when(jwtTokenProvider.validateToken(accessToken)).thenReturn(false);

        // then
        tokenManagementService.expireAccessToken(accessToken);
        verify(invalidAccessTokenRepository, atMost(0)).save(any());
    }

    @Test
    @DisplayName("입력된 액세스 토큰이 이미 블랙리스트에 등록되어 있으면 저장하지 않는다.")
    void expireAccessToken_existToken() {
        // given
        String accessToken = "accessCode";

        // when
        when(jwtTokenProvider.validateToken(accessToken)).thenReturn(true);
        when(invalidAccessTokenRepository.existsById(any())).thenReturn(true);

        // then
        tokenManagementService.expireAccessToken(accessToken);
        verify(invalidAccessTokenRepository, atMost(0)).save(any());
    }

    @Test
    @DisplayName("유효한 리프레시 토큰이 입력되고, 데이터베이스에 저장되어 있지 않다면 데이터베이스에 저장한다.")
    void expireRefreshToken_saveRepository() {
        // given
        String refreshToken = "refresh token";

        // when
        when(jwtTokenProvider.validateRefreshToken(refreshToken)).thenReturn(true);
        when(invalidRefreshTokenRepository.existsById(any())).thenReturn(false);

        // then
        tokenManagementService.expireRefreshToken(refreshToken);
        verify(invalidRefreshTokenRepository, atLeast(1)).save(any());
    }


    @Test
    @DisplayName("리프레시 토큰이 유효하지 않으면 데이터베이스에 저장하지 않는다.")
    void expireRefreshToken_invalidToken() {
        // given
        String refreshToken = "refresh token";

        // when
        when(jwtTokenProvider.validateRefreshToken(refreshToken)).thenReturn(false);

        // then
        tokenManagementService.expireRefreshToken(refreshToken);
        verify(invalidRefreshTokenRepository, atMost(0)).save(any());
    }

    @Test
    @DisplayName("입력된 액세스 토큰이 이미 블랙리스트에 등록되어 있으면 저장하지 않는다.")
    void expireRefreshToken_existToken() {
        // given
        String refreshToken = "refresh token";

        // when
        when(jwtTokenProvider.validateRefreshToken(refreshToken)).thenReturn(true);
        when(invalidRefreshTokenRepository.existsById(any())).thenReturn(true);

        // then
        tokenManagementService.expireRefreshToken(refreshToken);
        verify(invalidRefreshTokenRepository, atMost(0)).save(any());
    }
}

package fashionable.simba.yanawaserver.global.token.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.token.exception.InvalidTokenException;
import fashionable.simba.yanawaserver.global.token.exception.InvalidTokenTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenManagerTest {
    private static final String NOT_NUMBER = "String";
    private static final Long USER_ID = 1L;
    private static final String ACCESS_TOKEN = "access token";
    private static final String REFRESH_TOKEN = "refresh token";
    private static final String INVALID_TOKEN = "invalid token";
    private static final ValidAccessToken VALID_ACCESS_TOKEN = new ValidAccessToken(USER_ID, ACCESS_TOKEN);
    private static final ValidRefreshToken VALID_REFRESH_TOKEN = new ValidRefreshToken(USER_ID, REFRESH_TOKEN);
    private static final ValidAccessToken INVALID_ACCESS_TOKEN = new ValidAccessToken(USER_ID, INVALID_TOKEN);
    private static final ValidRefreshToken INVALID_REFRESH_TOKEN = new ValidRefreshToken(USER_ID, INVALID_TOKEN);
    @Mock
    private ValidAccessTokenStorage validAccessTokenRepository;
    @Mock
    private ValidRefreshTokenStorage validRefreshTokenRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private TokenManager tokenManager;

    @Test
    @DisplayName("토큰을 입력받아 인증된 토큰 저장소에 저장한다.")
    void saveValidAccessToken() {
        // when
        when(validAccessTokenRepository.save(VALID_ACCESS_TOKEN))
            .thenReturn(VALID_ACCESS_TOKEN);
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn("1");

        // then
        assertDoesNotThrow(
            () -> tokenManager.manageAccessToken(ACCESS_TOKEN)
        );
        verify(validAccessTokenRepository, atLeastOnce()).save(VALID_ACCESS_TOKEN);
    }

    @Test
    @DisplayName("저장하기 전에 토큰이 유효하지 않으면 InvalidTokenException 예외가 발생한다.")
    void saveValidAccessToken_invalidAccessToken() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(false);

        // then
        assertThatThrownBy(
            () -> tokenManager.manageAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("Principal이 숫자 형태가 아니면 InvalidTokenTypeException 예외가 발생한다.")
    void saveValidAccessToken_invalidPrincipal() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn(NOT_NUMBER);

        // then
        assertThatThrownBy(
            () -> tokenManager.manageAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenTypeException.class);
    }


    @Test
    @DisplayName("리프레시 토큰을 입력받아 인증된 토큰 저장소에 저장한다.")
    void saveValidRefreshToken() {
        // when
        when(validRefreshTokenRepository.save(VALID_REFRESH_TOKEN))
            .thenReturn(VALID_REFRESH_TOKEN);
        when(jwtTokenProvider.validateRefreshToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipalByRefreshToken(ACCESS_TOKEN)).thenReturn("1");

        // then
        assertDoesNotThrow(
            () -> tokenManager.manageRefreshToken(ACCESS_TOKEN)
        );
        verify(validRefreshTokenRepository, atLeastOnce()).save(VALID_REFRESH_TOKEN);
    }

    @Test
    @DisplayName("저장하기 전에 토큰이 유효하지 않으면 InvalidTokenException 예외가 발생한다.")
    void saveValidRefreshToken_invalidRefreshToken() {
        // when
        when(jwtTokenProvider.validateRefreshToken(ACCESS_TOKEN)).thenReturn(false);

        // then
        assertThatThrownBy(
            () -> tokenManager.manageRefreshToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("Principal이 숫자 형태가 아니면 InvalidTokenTypeException 예외가 발생한다.")
    void saveValidRefreshToken_invalidPrincipal() {
        // when
        when(jwtTokenProvider.validateRefreshToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipalByRefreshToken(ACCESS_TOKEN)).thenReturn(NOT_NUMBER);

        // then
        assertThatThrownBy(
            () -> tokenManager.manageRefreshToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenTypeException.class);
    }

    @Test
    @DisplayName("토큰을 검증하기 전에 유효한 토큰이 아니면 InvalidTokenException 예외가 발생한다.")
    void verifyValidAccessToken_invalidAccessToken() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(false);

        // then
        assertThatThrownBy(
            () -> tokenManager.verifyAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("Principal 이 숫자 형태가 아니면 InvalidTokenTypeException 예외가 발생한다.")
    void verifyValidAccessToken_invalidPrincipal() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn(NOT_NUMBER);

        // then
        assertThatThrownBy(
            () -> tokenManager.manageAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenTypeException.class);
    }

    @Test
    @DisplayName("ValidTokenStorage 에 없다면 InvalidTokenException 예외를 발생한다.")
    void verifyValidAccessToken_nothing() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn(USER_ID.toString());
        when(validAccessTokenRepository.findById(USER_ID)).thenReturn(Optional.empty());
        // then
        assertThatThrownBy(
            () -> tokenManager.verifyAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("ValidTokenStorage 에서 꺼낸 토큰과 같지 않다면 InvalidTokenException 예외가 발생한다.")
    void verifyValidAccessToken_verifyStorage() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn(USER_ID.toString());
        when(validAccessTokenRepository.findById(USER_ID)).thenReturn(Optional.of(INVALID_ACCESS_TOKEN));

        // then
        assertThatThrownBy(
            () -> tokenManager.verifyAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("토큰을 입력받아 인증된 토큰인지 확인한다.")
    void verifyValidToken() {
        // when
        when(jwtTokenProvider.validateToken(ACCESS_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipal(ACCESS_TOKEN)).thenReturn(USER_ID.toString());
        when(validAccessTokenRepository.findById(USER_ID)).thenReturn(Optional.of(VALID_ACCESS_TOKEN));

        // then
        assertDoesNotThrow(
            () -> tokenManager.verifyAccessToken(ACCESS_TOKEN)
        );
    }

    @Test
    @DisplayName("리프레시 토큰을 입력받아 인증된 리프레시 토큰인지 확인한다.")
    void verifyValidRefreshToken() {
        // when
        when(jwtTokenProvider.validateRefreshToken(REFRESH_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipalByRefreshToken(REFRESH_TOKEN)).thenReturn(USER_ID.toString());
        when(validRefreshTokenRepository.findById(USER_ID)).thenReturn(Optional.of(VALID_REFRESH_TOKEN));

        // then
        assertDoesNotThrow(
            () -> tokenManager.verifyRefreshToken(REFRESH_TOKEN)
        );
    }

    @Test
    @DisplayName("리프레시 토큰이 ValidTokenStorage 에 없다면 InvalidTokenException 예외를 발생한다.")
    void verifyValidRefreshToken_nothing() {
        // when
        when(jwtTokenProvider.validateRefreshToken(REFRESH_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipalByRefreshToken(REFRESH_TOKEN)).thenReturn(USER_ID.toString());
        when(validRefreshTokenRepository.findById(USER_ID)).thenReturn(Optional.empty());
        // then
        assertThatThrownBy(
            () -> tokenManager.verifyRefreshToken(REFRESH_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("ValidTokenStorage 에서 꺼낸 리프레시 토큰과 같지 않다면 InvalidTokenException 예외가 발생한다.")
    void verifyValidRefreshToken_verifyStorage() {
        // when
        when(jwtTokenProvider.validateRefreshToken(REFRESH_TOKEN)).thenReturn(true);
        when(jwtTokenProvider.getPrincipalByRefreshToken(REFRESH_TOKEN)).thenReturn(USER_ID.toString());
        when(validRefreshTokenRepository.findById(USER_ID)).thenReturn(Optional.of(INVALID_REFRESH_TOKEN));

        // then
        assertThatThrownBy(
            () -> tokenManager.verifyRefreshToken(REFRESH_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("존재하지 않은 토큰 값을 입력받으면 InvalidTokenException 예외가 발생한다.")
    void expireAccessToken_invalidToken() {
        // when
        when(validAccessTokenRepository.findByAccessToken(ACCESS_TOKEN))
            .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(
            () -> tokenManager.expireAccessToken(ACCESS_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("토큰을 만료시킬 때 액세스 토큰 저장소에서 토큰을 제거한다.")
    void expireAccessToken_usingToken() {
        // when
        when(validAccessTokenRepository.findByAccessToken(ACCESS_TOKEN))
            .thenReturn(Optional.of(VALID_ACCESS_TOKEN));
        doNothing().when(validAccessTokenRepository).delete(VALID_ACCESS_TOKEN);

        tokenManager.expireAccessToken(ACCESS_TOKEN);

        verify(validAccessTokenRepository, atLeastOnce()).delete(VALID_ACCESS_TOKEN);
    }

    @Test
    @DisplayName("유효하지 않은 ID이면 InvalidTokenException 예외가 발생한다.")
    void expireAccessToken_invalidId() {
        // when
        when(validAccessTokenRepository.findById(USER_ID))
            .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(
            () -> tokenManager.expireAccessToken(USER_ID)
        ).isInstanceOf(InvalidTokenException.class);
    }


    @Test
    @DisplayName("토큰을 만료시킬 때 액세스 토큰 저장소에서 토큰을 제거한다.")
    void expireAccessToken_usingId() {
        // when
        when(validAccessTokenRepository.findById(USER_ID))
            .thenReturn(Optional.of(VALID_ACCESS_TOKEN));
        doNothing().when(validAccessTokenRepository).delete(VALID_ACCESS_TOKEN);

        tokenManager.expireAccessToken(USER_ID);

        verify(validAccessTokenRepository, atLeastOnce()).delete(VALID_ACCESS_TOKEN);
    }


    @Test
    @DisplayName("존재하지 않은 Refresh 토큰 값을 입력받으면 InvalidTokenException 예외가 발생한다.")
    void expireRefreshToken_invalidToken() {
        // when
        when(validRefreshTokenRepository.findByRefreshToken(REFRESH_TOKEN))
            .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(
            () -> tokenManager.expireRefreshToken(REFRESH_TOKEN)
        ).isInstanceOf(InvalidTokenException.class);
    }

    @Test
    @DisplayName("Refresh 토큰을 만료시킬 때 액세스 토큰 저장소에서 토큰을 제거한다.")
    void expireRefreshToken_usingToken() {
        // when
        when(validRefreshTokenRepository.findByRefreshToken(REFRESH_TOKEN))
            .thenReturn(Optional.of(VALID_REFRESH_TOKEN));
        doNothing().when(validRefreshTokenRepository).delete(VALID_REFRESH_TOKEN);

        tokenManager.expireRefreshToken(REFRESH_TOKEN);

        verify(validRefreshTokenRepository, atLeastOnce()).delete(VALID_REFRESH_TOKEN);
    }

    @Test
    @DisplayName("유효하지 않은 ID이면 InvalidTokenException 예외가 발생한다.")
    void expireRefreshToken_invalidId() {
        // when
        when(validRefreshTokenRepository.findById(USER_ID))
            .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(
            () -> tokenManager.expireRefreshToken(USER_ID)
        ).isInstanceOf(InvalidTokenException.class);
    }


    @Test
    @DisplayName("Refresh 토큰을 만료시킬 때 액세스 토큰 저장소에서 토큰을 제거한다.")
    void expireRefreshToken_usingId() {
        // when
        when(validRefreshTokenRepository.findById(USER_ID))
            .thenReturn(Optional.of(VALID_REFRESH_TOKEN));
        doNothing().when(validRefreshTokenRepository).delete(VALID_REFRESH_TOKEN);

        tokenManager.expireRefreshToken(USER_ID);

        verify(validRefreshTokenRepository, atLeastOnce()).delete(VALID_REFRESH_TOKEN);
    }

}

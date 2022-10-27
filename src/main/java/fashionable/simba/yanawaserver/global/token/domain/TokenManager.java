package fashionable.simba.yanawaserver.global.token.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.token.exception.InvalidTokenException;
import fashionable.simba.yanawaserver.global.token.exception.InvalidTokenTypeException;
import org.apache.commons.lang3.StringUtils;

public class TokenManager {
    private final ValidAccessTokenStorage validAccessTokenRepository;
    private final ValidRefreshTokenStorage validRefreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenManager(ValidAccessTokenStorage validAccessTokenRepository, ValidRefreshTokenStorage validRefreshTokenRepository, JwtTokenProvider jwtTokenProvider) {
        this.validAccessTokenRepository = validAccessTokenRepository;
        this.validRefreshTokenRepository = validRefreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void manageAccessToken(String accessToken) {
        validateAccessToken(accessToken);

        Long userId = getUserId(jwtTokenProvider.getPrincipal(accessToken));
        ValidAccessToken validAccessToken = new ValidAccessToken(userId, accessToken);
        validAccessTokenRepository.save(validAccessToken);
    }

    public void manageRefreshToken(String refreshToken) {
        validateRefreshToken(refreshToken);

        Long userId = getUserId(jwtTokenProvider.getPrincipalByRefreshToken(refreshToken));
        ValidRefreshToken validRefreshToken = new ValidRefreshToken(userId, refreshToken);
        validRefreshTokenRepository.save(validRefreshToken);
    }

    public void verifyAccessToken(String accessToken) {
        validateAccessToken(accessToken);
        Long userId = getUserId(jwtTokenProvider.getPrincipal(accessToken));
        ValidAccessToken validAccessToken = getValidAccessTokenById(userId);

        if (!validAccessToken.getAccessToken().equals(accessToken)) {
            throw new InvalidTokenException("유효하지 않은 엑세스 토큰입니다.");
        }
    }

    public void verifyRefreshToken(String refreshToken) {
        validateRefreshToken(refreshToken);
        Long userId = getUserId(jwtTokenProvider.getPrincipalByRefreshToken(refreshToken));
        ValidRefreshToken validRefreshToken = getValidRefreshToken(userId);

        if (!validRefreshToken.getRefreshToken().equals(refreshToken)) {
            throw new InvalidTokenException("유효하지 않은 엑세스 토큰입니다.");
        }
    }

    public void expireAccessToken(Long userId) {
        ValidAccessToken accessToken = getValidAccessTokenById(userId);
        expireAccessToken(accessToken);
    }

    public void expireAccessToken(String accessToken) {
        ValidAccessToken validAccessToken = getValidAccessTokenByToken(accessToken);
        expireAccessToken(validAccessToken);
    }

    public void expireRefreshToken(Long userId) {
        ValidRefreshToken accessToken = getValidRefreshTokenById(userId);
        expireRefreshToken(accessToken);
    }

    public void expireRefreshToken(String refreshToken) {
        ValidRefreshToken validRefreshToken = getValidRefreshTokenByToken(refreshToken);
        expireRefreshToken(validRefreshToken);
    }

    private ValidRefreshToken getValidRefreshTokenByToken(String refreshToken) {
        return validRefreshTokenRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new InvalidTokenException("유효하지 않은 토큰입니다."));
    }

    private ValidAccessToken getValidAccessTokenByToken(String accessToken) {
        return validAccessTokenRepository.findByAccessToken(accessToken)
            .orElseThrow(() -> new InvalidTokenException("유효하지 않은 토큰입니다."));
    }

    private ValidRefreshToken getValidRefreshTokenById(Long userId) {
        return validRefreshTokenRepository.findById(userId)
            .orElseThrow(() -> new InvalidTokenException("사용자 정보가 존재하지 않습니다."));
    }

    private ValidAccessToken getValidAccessTokenById(Long userId) {
        return validAccessTokenRepository.findById(userId)
            .orElseThrow(() -> new InvalidTokenException("사용자 정보가 존재하지 않습니다."));
    }

    private ValidRefreshToken getValidRefreshToken(Long userId) {
        return validRefreshTokenRepository.findById(userId)
            .orElseThrow(() -> new InvalidTokenException("사용자 정보가 존재하지 않습니다."));
    }

    private void expireAccessToken(ValidAccessToken accessToken) {
        validAccessTokenRepository.delete(accessToken);
    }

    private void expireRefreshToken(ValidRefreshToken refreshToken) {
        validRefreshTokenRepository.delete(refreshToken);
    }

    private void validateAccessToken(String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw new InvalidTokenException("유효하지 않은 액세스 토큰은 저장할 수 없습니다.");
        }
    }

    private void validateRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new InvalidTokenException("유효하지 않은 리프레시 토큰은 저장할 수 없습니다.");
        }
    }

    private Long getUserId(String principal) {
        if (!StringUtils.isNumeric(principal)) {
            throw new InvalidTokenTypeException("유효하지 않은 Principal 입니다. Principal 은 숫자 타입만 가능합니다.");
        }
        return Long.parseLong(principal);
    }

}

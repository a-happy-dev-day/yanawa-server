package fashionable.simba.yanawaserver.token.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.token.exception.InvalidTokenException;
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
            throw InvalidTokenException.invalidAccessToken();
        }
    }

    public void verifyRefreshToken(String refreshToken) {
        validateRefreshToken(refreshToken);
        Long userId = getUserId(jwtTokenProvider.getPrincipalByRefreshToken(refreshToken));
        ValidRefreshToken validRefreshToken = getValidRefreshToken(userId);

        if (!validRefreshToken.getRefreshToken().equals(refreshToken)) {
            throw InvalidTokenException.invalidRefreshToken();
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
            .orElseThrow(InvalidTokenException::expiresToken);
    }

    private ValidAccessToken getValidAccessTokenByToken(String accessToken) {
        return validAccessTokenRepository.findByAccessToken(accessToken)
            .orElseThrow(InvalidTokenException::expiresToken);
    }

    private ValidRefreshToken getValidRefreshTokenById(Long userId) {
        return validRefreshTokenRepository.findById(userId)
            .orElseThrow(InvalidTokenException::invalidUser);
    }

    private ValidAccessToken getValidAccessTokenById(Long userId) {
        return validAccessTokenRepository.findById(userId)
            .orElseThrow(InvalidTokenException::invalidUser);
    }

    private ValidRefreshToken getValidRefreshToken(Long userId) {
        return validRefreshTokenRepository.findById(userId)
            .orElseThrow(InvalidTokenException::invalidUser);
    }

    private void expireAccessToken(ValidAccessToken accessToken) {
        validAccessTokenRepository.delete(accessToken);
    }

    private void expireRefreshToken(ValidRefreshToken refreshToken) {
        validRefreshTokenRepository.delete(refreshToken);
    }

    private void validateAccessToken(String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) {
            throw InvalidTokenException.invalidAccessToken();
        }
    }

    private void validateRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw InvalidTokenException.invalidRefreshToken();
        }
    }

    private Long getUserId(String principal) {
        if (!StringUtils.isNumeric(principal)) {
            throw InvalidTokenException.invalidTokenType();
        }
        return Long.parseLong(principal);
    }

}

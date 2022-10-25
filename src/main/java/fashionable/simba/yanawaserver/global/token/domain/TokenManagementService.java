package fashionable.simba.yanawaserver.global.token.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManagementService {
    private final InvalidAccessTokenRepository invalidAccessTokenRepository;
    private final InvalidRefreshTokenRepository invalidRefreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenManagementService(InvalidAccessTokenRepository invalidAccessTokenRepository, InvalidRefreshTokenRepository invalidRefreshTokenRepository, JwtTokenProvider jwtTokenProvider) {
        this.invalidAccessTokenRepository = invalidAccessTokenRepository;
        this.invalidRefreshTokenRepository = invalidRefreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void expireAccessToken(String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) {
            return;
        }

        if (invalidAccessTokenRepository.existsById(accessToken)) {
            return;
        }

        Date date = jwtTokenProvider.getExpiredDate(accessToken);
        InvalidAccessToken invalidAccessToken = new InvalidAccessToken(accessToken, date);
        invalidAccessTokenRepository.save(invalidAccessToken);
    }

    public void expireRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            return;
        }

        if (invalidRefreshTokenRepository.existsById(refreshToken)) {
            return;
        }

        Date date = jwtTokenProvider.getExpiredDateByRefreshToken(refreshToken);
        InvalidRefreshToken invalidRefreshToken = new InvalidRefreshToken(refreshToken, date);
        invalidRefreshTokenRepository.save(invalidRefreshToken);
    }
}

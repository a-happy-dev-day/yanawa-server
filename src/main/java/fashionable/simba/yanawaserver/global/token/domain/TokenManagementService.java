package fashionable.simba.yanawaserver.global.token.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManagementService {
    private final ValidAccessTokenRepository invalidAccessTokenRepository;
    private final ValidRefreshTokenRepository invalidRefreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenManagementService(ValidAccessTokenRepository invalidAccessTokenRepository, ValidRefreshTokenRepository invalidRefreshTokenRepository, JwtTokenProvider jwtTokenProvider) {
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
        ValidAccessToken invalidAccessToken = new ValidAccessToken(accessToken, date);
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
        ValidRefreshToken invalidRefreshToken = new ValidRefreshToken(refreshToken, date);
        invalidRefreshTokenRepository.save(invalidRefreshToken);
    }
}

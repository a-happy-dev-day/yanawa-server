package fashionable.simba.yanawaserver.auth.domain;

import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import org.springframework.stereotype.Service;

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
        if (!jwtTokenProvider.validateToken(accessToken) || invalidAccessTokenRepository.exist(accessToken)) {
            return;
        }
        invalidAccessTokenRepository.save(accessToken);
    }

    public void expireRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken) || invalidRefreshTokenRepository.exist(refreshToken)) {
            return;
        }
        invalidRefreshTokenRepository.save(refreshToken);
    }
}

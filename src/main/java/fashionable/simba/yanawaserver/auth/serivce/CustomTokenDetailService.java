package fashionable.simba.yanawaserver.auth.serivce;

import fashionable.simba.yanawaserver.auth.domain.InvalidAccessTokenRepository;
import fashionable.simba.yanawaserver.auth.domain.InvalidRefreshTokenRepository;
import fashionable.simba.yanawaserver.global.token.TokenDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomTokenDetailService implements TokenDetailsService {
    private final InvalidAccessTokenRepository invalidAccessTokenRepository;
    private final InvalidRefreshTokenRepository invalidRefreshTokenRepository;

    public CustomTokenDetailService(InvalidAccessTokenRepository invalidAccessTokenRepository, InvalidRefreshTokenRepository invalidRefreshTokenRepository) {
        this.invalidAccessTokenRepository = invalidAccessTokenRepository;
        this.invalidRefreshTokenRepository = invalidRefreshTokenRepository;
    }

    /**
     * 유효한 토큰이면 true를 반환한다.
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean validateAccessToken(String accessToken) {
        return !invalidAccessTokenRepository.existsById(accessToken);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return !invalidRefreshTokenRepository.existsById(refreshToken);
    }
}

package fashionable.simba.yanawaserver.auth.serivce;

import fashionable.simba.yanawaserver.auth.domain.InvalidAccessTokenRepository;
import fashionable.simba.yanawaserver.global.token.TokenDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomTokenDetailService implements TokenDetailsService {
    private final InvalidAccessTokenRepository invalidAccessTokenRepository;

    public CustomTokenDetailService(InvalidAccessTokenRepository invalidAccessTokenRepository) {
        this.invalidAccessTokenRepository = invalidAccessTokenRepository;
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

}

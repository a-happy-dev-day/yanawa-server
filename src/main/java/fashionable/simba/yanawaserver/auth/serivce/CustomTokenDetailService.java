package fashionable.simba.yanawaserver.auth.serivce;

import fashionable.simba.yanawaserver.token.domain.TokenDetailsService;
import fashionable.simba.yanawaserver.token.domain.TokenManager;

public class CustomTokenDetailService implements TokenDetailsService {
    private final TokenManager tokenManager;

    public CustomTokenDetailService(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }


    /**
     * 유효한 accessToken 이면 true를 반환한다.
     *
     * @param accessToken
     * @return
     */
    @Override
    public void validateAccessToken(String accessToken) {
        tokenManager.verifyAccessToken(accessToken);
    }

    /**
     * 유효한 refreshToken 이면 true를 반환한다.
     *
     * @param refreshToken
     * @return
     */
    public void validateRefreshToken(String refreshToken) {
        tokenManager.verifyRefreshToken(refreshToken);
    }
}

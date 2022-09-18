package fashionable.simba.yanawaserver.auth.domain;

import fashionable.simba.yanawaserver.auth.domain.AccessToken;
import fashionable.simba.yanawaserver.auth.domain.RefreshToken;

public class AuthenticationToken {
    private AccessToken accessToken;
    private RefreshToken refreshToken;

    public AuthenticationToken(String accessToken, String refreshToken) {
        this.accessToken = new AccessToken(accessToken);
        this.refreshToken = new RefreshToken(refreshToken);
    }

    public String getAccessToken() {
        return accessToken.getAccessToken();
    }

    public String getRefreshToken() {
        return refreshToken.getRefreshToken();
    }
}

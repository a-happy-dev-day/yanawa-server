package fashionable.simba.yanawaserver.auth.domain;

public class AuthorizationToken {
    private AuthorizationAccessToken accessToken;
    private AuthorizationRefreshToken refreshToken;

    public AuthorizationToken(String accessToken, String refreshToken) {
        this.accessToken = new AuthorizationAccessToken(accessToken);
        this.refreshToken = new AuthorizationRefreshToken(refreshToken);
    }

    public String getAccessToken() {
        return accessToken.getAccessToken();
    }

    public String getRefreshToken() {
        return refreshToken.getRefreshToken();
    }
}

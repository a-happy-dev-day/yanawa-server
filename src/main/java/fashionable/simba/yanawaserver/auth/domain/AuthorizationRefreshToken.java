package fashionable.simba.yanawaserver.auth.domain;

public class AuthorizationRefreshToken {
    private String refreshToken;

    private AuthorizationRefreshToken() {/*no-op*/}

    public AuthorizationRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

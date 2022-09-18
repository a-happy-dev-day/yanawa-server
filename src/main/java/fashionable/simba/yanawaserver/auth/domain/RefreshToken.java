package fashionable.simba.yanawaserver.auth.domain;

public class RefreshToken {
    private String refreshToken;

    private RefreshToken() {/*no-op*/}

    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

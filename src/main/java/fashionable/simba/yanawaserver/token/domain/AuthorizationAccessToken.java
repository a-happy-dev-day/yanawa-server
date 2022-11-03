package fashionable.simba.yanawaserver.token.domain;

public class AuthorizationAccessToken {
    private String accessToken;

    private AuthorizationAccessToken() {/*no-op*/}

    public AuthorizationAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

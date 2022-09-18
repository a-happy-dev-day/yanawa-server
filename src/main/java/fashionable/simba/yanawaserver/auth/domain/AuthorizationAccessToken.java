package fashionable.simba.yanawaserver.auth.domain;

public class AuthorizationAccessToken {
    private String accessToken;

    public AuthorizationAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

package fashionable.simba.yanawaserver.global.domain;

public class AuthorizationAccessToken {
    private String accessToken;

    public AuthorizationAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

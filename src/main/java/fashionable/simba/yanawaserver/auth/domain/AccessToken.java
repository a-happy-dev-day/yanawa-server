package fashionable.simba.yanawaserver.auth.domain;

public class AccessToken {
    private String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

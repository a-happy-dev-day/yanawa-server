package fashionable.simba.yanawaserver.members.domain;

public class AccessToken {
    private final String token;

    public AccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

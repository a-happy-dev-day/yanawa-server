package fashionable.simba.yanawaserver.auth.token;

public class TokenRequest {
    private String username;

    public TokenRequest() {
    }

    public TokenRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

package fashionable.simba.yanawaserver.auth.ui;

public class TokenRequest {
    private String username;

    private TokenRequest() {
    }

    public TokenRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

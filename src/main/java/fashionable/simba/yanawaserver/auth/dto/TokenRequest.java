package fashionable.simba.yanawaserver.auth.dto;

public class TokenRequest {
    private Object username;

    private TokenRequest() {
    }

    public TokenRequest(Object username) {
        this.username = username;
    }

    public Object getUsername() {
        return username;
    }
}

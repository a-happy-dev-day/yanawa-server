package fashionable.simba.yanawaserver.auth.ui;

public class TokenRequest {
    private String accessCode;

    private TokenRequest() {
    }

    public TokenRequest(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}

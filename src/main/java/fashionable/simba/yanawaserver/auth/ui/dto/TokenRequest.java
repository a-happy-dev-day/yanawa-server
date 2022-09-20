package fashionable.simba.yanawaserver.auth.ui.dto;

public class TokenRequest {
    private String accessCode;

    private TokenRequest() {/*no-op*/}

    public TokenRequest(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}

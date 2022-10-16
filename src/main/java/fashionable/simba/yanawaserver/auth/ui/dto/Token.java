package fashionable.simba.yanawaserver.auth.ui.dto;

public class Token {
    private String accessCode;

    private Token() {/*no-op*/}

    public Token(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}

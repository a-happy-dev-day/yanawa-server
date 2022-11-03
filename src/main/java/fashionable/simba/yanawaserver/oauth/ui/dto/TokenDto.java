package fashionable.simba.yanawaserver.oauth.ui.dto;

public class TokenDto {
    private String accessCode;

    private TokenDto() {/*no-op*/}

    public TokenDto(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}

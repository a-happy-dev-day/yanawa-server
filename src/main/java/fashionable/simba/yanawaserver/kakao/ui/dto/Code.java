package fashionable.simba.yanawaserver.kakao.ui.dto;

public class Code {
    private String accessCode;

    private Code() {/*no-op*/}

    public Code(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }
}

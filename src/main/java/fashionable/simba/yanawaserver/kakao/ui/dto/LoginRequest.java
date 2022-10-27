package fashionable.simba.yanawaserver.kakao.ui.dto;

public class LoginRequest {
    private String username;
    private String password;

    private LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

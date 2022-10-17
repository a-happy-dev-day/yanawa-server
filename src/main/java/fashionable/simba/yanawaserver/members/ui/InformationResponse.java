package fashionable.simba.yanawaserver.members.ui;

public class InformationResponse {
    private final String nickname;
    private final boolean isAvailable;

    public InformationResponse(String nickname, boolean isAvailable) {
        this.nickname = nickname;
        this.isAvailable = isAvailable;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

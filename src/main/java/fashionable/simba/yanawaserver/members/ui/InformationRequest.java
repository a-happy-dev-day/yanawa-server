package fashionable.simba.yanawaserver.members.ui;

import java.time.LocalDate;

public class InformationRequest {
    private String nickname;
    private LocalDate localDate;
    private float level;

    private InformationRequest() {/*no-op*/}

    public InformationRequest(String nickname, LocalDate localDate, float level) {
        this.nickname = nickname;
        this.localDate = localDate;
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public float getLevel() {
        return level;
    }
}

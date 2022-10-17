package fashionable.simba.yanawaserver.members.ui;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InformationRequest {
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private BigDecimal level;

    private InformationRequest() {/*no-op*/}

    public InformationRequest(String nickname, LocalDate birthDate, BigDecimal level) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public BigDecimal getLevel() {
        return level;
    }
}

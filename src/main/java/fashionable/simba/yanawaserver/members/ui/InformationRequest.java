package fashionable.simba.yanawaserver.members.ui;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import fashionable.simba.yanawaserver.members.domain.MemberSex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InformationRequest {
    private String nickname;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;
    private BigDecimal level;
    private MemberSex sex;

    private InformationRequest() {/*no-op*/}

    public InformationRequest(String nickname, LocalDate birthDate, BigDecimal level, MemberSex sex) {
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.level = level;
        this.sex = sex;
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

    public MemberSex getSex() {
        return sex;
    }
}

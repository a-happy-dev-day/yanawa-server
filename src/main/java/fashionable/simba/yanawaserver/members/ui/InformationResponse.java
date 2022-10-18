package fashionable.simba.yanawaserver.members.ui;

import fashionable.simba.yanawaserver.members.domain.MemberBirthDate;
import fashionable.simba.yanawaserver.members.domain.MemberLevel;
import fashionable.simba.yanawaserver.members.domain.MemberSex;

public class InformationResponse {
    private final String nickname;
    private final String email;
    private final MemberSex sex;
    private final MemberBirthDate birthDate;
    private final MemberLevel level;
    private final boolean first;

    public InformationResponse(String nickname, String email, MemberSex sex, MemberBirthDate birthDate, MemberLevel level, boolean first) {
        this.nickname = nickname;
        this.email = email;
        this.sex = sex;
        this.birthDate = birthDate;
        this.level = level;
        this.first = first;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isFirst() {
        return first;
    }

    public String getEmail() {
        return email;
    }

    public MemberSex getSex() {
        return sex;
    }

    public MemberBirthDate getBirthDate() {
        return birthDate;
    }

    public MemberLevel getLevel() {
        return level;
    }
}

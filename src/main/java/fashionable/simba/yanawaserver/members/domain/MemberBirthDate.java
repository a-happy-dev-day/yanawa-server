package fashionable.simba.yanawaserver.members.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidBirthDateException;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class MemberBirthDate {
    private LocalDate birth;

    protected MemberBirthDate() {/*no-op*/}

    public MemberBirthDate(LocalDate birth) {
        if (birth.isAfter(LocalDate.now())) {
            throw new InvalidBirthDateException("생일이 유효하지 않습니다.");
        }
        this.birth = birth;
    }

    public LocalDate getBirth() {
        return birth;
    }
}

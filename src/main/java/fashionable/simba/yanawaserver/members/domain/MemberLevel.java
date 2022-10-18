package fashionable.simba.yanawaserver.members.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidLevelException;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Embeddable
public class MemberLevel {
    private BigDecimal value;

    protected MemberLevel() {/*no-op*/}

    public MemberLevel(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(BigDecimal.valueOf(6)) > 0) {
            throw new InvalidLevelException();
        }

        BigDecimal decimal = value.divide(BigDecimal.valueOf(0.5), MathContext.DECIMAL128);

        if (!decimal.equals(decimal.setScale(0, RoundingMode.DOWN))) {
            throw new InvalidLevelException();
        }

        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}

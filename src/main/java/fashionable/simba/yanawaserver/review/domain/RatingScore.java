package fashionable.simba.yanawaserver.review.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidLevelException;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Embeddable
public class RatingScore {
    private BigDecimal score;

    public RatingScore() {
        /*no-op*/
    }

    public RatingScore(BigDecimal score) {
        if (score.compareTo(BigDecimal.ZERO) < 0 || score.compareTo(BigDecimal.valueOf(5)) >= 0) {
            throw new InvalidLevelException();
        }
        BigDecimal decimal = score.divide(BigDecimal.valueOf(0.5), MathContext.DECIMAL128);

        if (!decimal.equals(decimal.setScale(0, RoundingMode.DOWN))) {
            throw new InvalidLevelException();
        }

        this.score = score;
    }

    public BigDecimal getScore() {
        return score;
    }
}

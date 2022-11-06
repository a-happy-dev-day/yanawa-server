package fashionable.simba.yanawaserver.members.domain;

import java.math.BigDecimal;

public interface MemberUpdateReview {
    void updateRating(Long memberId, BigDecimal rating);

    void updateManner(Long memberId, BigDecimal mannerTemperature);

}

package fashionable.simba.yanawaserver.members.domain;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MemberUpdateReview {
    void updateRating(Long memberId, BigDecimal rating);

    void updateManner(Long memberId, BigDecimal mannerTemperature);

}

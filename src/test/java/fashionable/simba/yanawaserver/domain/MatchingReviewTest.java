package fashionable.simba.yanawaserver.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MatchingReviewTest {
    @Test
    @DisplayName("매칭후기 생성 테스트")
    void review_생성_Test() {
        UUID writerId = UUID.randomUUID();
        UUID partnerId = UUID.randomUUID();
        String details = "좋은 경기했습니다.";
        //
        MatchingReview matchingReview = new MatchingReview.MatchingReviewBuilder(
                writerId, partnerId)
                .setDetails(details)
                .build();
        //
        Assertions.assertEquals(writerId, matchingReview.getWriterId());
        Assertions.assertEquals(partnerId, matchingReview.getPartnerId());
        Assertions.assertEquals(details, matchingReview.getDetails());
    }
}

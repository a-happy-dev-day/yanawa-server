package fashionable.simba.yanawaserver.matching.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class MatchingReviewTest {
    @Test
    @DisplayName("매칭후기 생성 테스트")
    void review_생성_Test() {
        MatchingReview matchingReview = new MatchingReview(
            1L, 1L,
            2L,
            "매칭 후기 테스트입니다."
        );
        assertAll(
            () -> Assertions.assertEquals(1L, matchingReview.getMatchingId()),
            () -> Assertions.assertEquals(1L, matchingReview.getWriterId()),
            () -> Assertions.assertEquals(2L, matchingReview.getPartnerId()),
            () -> Assertions.assertEquals("매칭 후기 테스트입니다.", matchingReview.getDetails())
        );
    }
}

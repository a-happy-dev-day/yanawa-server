package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.NoPlayerDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

class MatchingReviewTest {
    @Test
    @DisplayName("매칭후기 생성 테스트")
    void review_생성_Test() {
        MatchingReview matchingReview = new MatchingReview(
                1L,
                2L,
                "매칭 후기 테스트입니다."
        );
        assertAll(
                () -> Assertions.assertEquals(matchingReview.getWriterId(), 1L),
                () -> Assertions.assertEquals(matchingReview.getPartnerId(), 2L),
                () -> Assertions.assertEquals(matchingReview.getDetails(), "매칭 후기 테스트입니다.")
        );
    }

    @Test
    @DisplayName("작성자나 파트너의 정보가 잘못 입력 되었을 경우, NoPlayerDataException이 발생한다.")
    void 리뷰_실패_테스트() {
        assertAll(
                () -> Assertions.assertThrows(NoPlayerDataException.class, () ->
                        new MatchingReview(null, 1L, "리뷰작성테스트")),
                () -> Assertions.assertThrows(NoPlayerDataException.class, () ->
                        new MatchingReview(1L, null, "리뷰작성테스트")),
                () -> Assertions.assertThrows(NoPlayerDataException.class, () ->
                        new MatchingReview(null, null, "리뷰작성테스트"))
        );
    }
}

package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.error.NoPlayerDataException;
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
        Assertions.assertDoesNotThrow(() ->
                new MatchingReview.MatchingReviewBuilder(writerId, partnerId)
                        .setDetails(details)
                        .build()
        );
    }

    @Test
    @DisplayName("작성자나 파트너의 정보가 잘못 입력 되었을 경우, NoPlayerDataException이 발생한다.")
    void 리뷰_실패_테스트() {
        UUID writerId = null;
        UUID partnerId = null;
        //
        Assertions.assertThrows(NoPlayerDataException.class, () ->
                new MatchingReview.MatchingReviewBuilder(writerId, partnerId)
                        .build()
        );
    }
}

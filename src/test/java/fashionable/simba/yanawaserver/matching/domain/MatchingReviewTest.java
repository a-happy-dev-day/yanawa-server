package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.NoPlayerDataException;
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
                new MatchingReview.Builder(writerId, partnerId)
                        .setDetails(details)
                        .build()
        );
    }

    @Test
    @DisplayName("작성자나 파트너의 정보가 잘못 입력 되었을 경우, NoPlayerDataException이 발생한다.")
    void 리뷰_실패_테스트() {
        Assertions.assertThrows(NoPlayerDataException.class, () ->
                new MatchingReview.Builder(UUID.randomUUID(), null)
                        .build()
        );
        Assertions.assertThrows(NoPlayerDataException.class, () ->
                new MatchingReview.Builder(null, UUID.randomUUID())
                        .build()
        );
        Assertions.assertThrows(NoPlayerDataException.class, () ->
                new MatchingReview.Builder(null, null)
                        .build()
        );
    }

    @Test
    @DisplayName("리뷰 생성시 작성자 확인")
    void 리뷰_성공_테스트() {
        UUID writerId = UUID.randomUUID();
        MatchingReview matchingReview = new MatchingReview.Builder(writerId, UUID.randomUUID())
                .setDetails("수고하셨습니다.")
                .build();
        //
        Assertions.assertEquals(writerId, matchingReview.getWriterId());
    }

    @Test
    @DisplayName("리뷰 생성시 파트너 확인")
    void 리뷰_성공_테스트2() {
        UUID partnerId = UUID.randomUUID();
        MatchingReview matchingReview = new MatchingReview.Builder(UUID.randomUUID(), partnerId)
                .setDetails("수고하셨습니다.")
                .build();
        //
        Assertions.assertEquals(partnerId, matchingReview.getPartnerId());
    }

    @Test
    @DisplayName("리뷰 생성시, ")
    void 리뷰_실패_테스트2() {
        UUID partnerId = UUID.randomUUID();
        MatchingReview matchingReview = new MatchingReview.Builder(UUID.randomUUID(), partnerId)
                .setDetails("수고하셨습니다.")
                .build();
        //
        Assertions.assertEquals(partnerId, matchingReview.getPartnerId());
    }

    @Test
    @DisplayName("리뷰 생성시 상세내용 테스트")
    void 리뷰_성공_테스트3() {
        UUID partnerId = UUID.randomUUID();
        MatchingReview matchingReview = new MatchingReview.Builder(UUID.randomUUID(), partnerId)
                .setDetails("수고하셨습니다.")
                .build();
        //
        Assertions.assertEquals("수고하셨습니다.", matchingReview.getDetails());
    }
}

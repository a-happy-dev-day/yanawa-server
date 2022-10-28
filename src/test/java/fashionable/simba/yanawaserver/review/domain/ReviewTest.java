package fashionable.simba.yanawaserver.review.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReviewTest {

    Review review = new Review(1L, 1L, 1L, new RatingScore(BigDecimal.valueOf(3.0)), MannerTemperatureType.EXCELLENT, 1L, "후기");

    @Test
    @DisplayName("평가를 생성한다.")
    void reviewCreateTest() {
        assertAll(
            () -> assertThat(review.getId()).isEqualTo(1L),
            () -> assertThat(review.getParticipantId()).isEqualTo(1L),
            () -> assertThat(review.getRecruitmentId()).isEqualTo(1L),
            () -> assertThat(review.getRatingScore().getScore()).isEqualTo(BigDecimal.valueOf(3.0)),
            () -> assertThat(review.getMannerTemperature()).isEqualTo(MannerTemperatureType.EXCELLENT),
            () -> assertThat(review.getUserId()).isEqualTo(1L),
            () -> assertThat(review.getDetail()).isEqualTo("후기")
        );
    }

    @Test
    @DisplayName("평가 점수를 수정한다.")
    void editRatingScore() {
        review.editRatingScore(new RatingScore(BigDecimal.valueOf(2.0)));

        assertThat(review.getRatingScore().getScore()).isEqualTo(BigDecimal.valueOf(2.0));
    }

    @Test
    @DisplayName("매너 점수를 수정한다.")
    void editMannerTemperature() {
        review.editMannerTemperature(MannerTemperatureType.GOOD);

        assertThat(review.getMannerTemperature()).isEqualTo(MannerTemperatureType.GOOD);
    }
}

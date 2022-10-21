package fashionable.simba.yanawaserver.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RatingTest {

    Rating rating;

    @BeforeEach
    void setUp() {
        rating = new Rating(1L, 1L, 1L, 3.5, 1L, 1L, "후기");
    }

    @Test
    @DisplayName("평가를 생성한다.")
    void ratingTest() {
        org.junit.jupiter.api.Assertions.assertAll(
            () -> assertThat(rating.getId()).isEqualTo(1L),
            () -> assertThat(rating.getParticipantId()).isEqualTo(1L),
            () -> assertThat(rating.getRecruitmentId()).isEqualTo(1L),
            () -> assertThat(rating.getRatingScore()).isEqualTo(3.5),
            () -> assertThat(rating.getMannerTemperature()).isEqualTo(1L),
            () -> assertThat(rating.getUserId()).isEqualTo(1L),
            () -> assertThat(rating.getDetail()).isEqualTo("후기")
        );
    }

    @Test
    @DisplayName("평가 점수를 수정한다.")
    void editRatingScore() {
        rating.editRatingScore(3.0);

        assertThat(rating.getRatingScore()).isEqualTo(3.0);
    }

    @Test
    @DisplayName("매너 점수를 수정한다.")
    void editMannerTemperature() {
        rating.editMannerTemperature(2L);

        assertThat(rating.getMannerTemperature()).isEqualTo(2L);
    }
}
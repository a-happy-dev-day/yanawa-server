package fashionable.simba.yanawaserver.review.domain;

import fashionable.simba.yanawaserver.members.exception.InvalidLevelException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

class ReviewScoreTest {

    @Test
    @DisplayName("평가점수 객체를 생성한다.")
    void createTest() {
        RatingScore ratingScore = new RatingScore();
    }

    @ParameterizedTest
    @DisplayName("점수가 0이상 5이하가 아닐경우, InvalidLevelException 발생한다.")
    @ValueSource(doubles = {-1, 6})
    void ScoreCreateTest(double value) {
        Assertions.assertThrows(InvalidLevelException.class, () -> new RatingScore(BigDecimal.valueOf(value)));
    }

    @Test
    @DisplayName("점수가 0.5단위가 아닌경우, 평가점수객체 생성시 InvalidLevelException 발생한다.")
    void ScoreCreateTest() {
        Assertions.assertThrows(InvalidLevelException.class, () -> new RatingScore(BigDecimal.valueOf(3.2)));
    }
}
package fashionable.simba.yanawaserver.review;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // 모집의 식별자, 참여자의 식별자와 평가 정보를 입력해 사용자의 능력을 평가한다.
    @GetMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = "save")
    public String findUserRatingScore() {
        Rating rating = null;
        ratingService.createRating(rating);
        return null;
    }


//    @GetMapping(
//        consumes = MediaType.APPLICATION_JSON_VALUE,
//    produces = MediaType.APPLICATION_JSON_VALUE,
//    value = "rating")
//    public String findUserRatingScore() {
//
//        return null;
//    }

    // 사용자 식별자를 입력해 사용자의 평가 점수를 조회한다.
}

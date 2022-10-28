package fashionable.simba.yanawaserver.review.ui;

import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.dto.ReviewRequest;
import fashionable.simba.yanawaserver.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request) {
        Review response = reviewService.createReview(request);

        return ResponseEntity.ok(response);
    }
}

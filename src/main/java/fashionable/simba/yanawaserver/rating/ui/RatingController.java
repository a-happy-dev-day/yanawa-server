package fashionable.simba.yanawaserver.rating.ui;

import fashionable.simba.yanawaserver.rating.domain.Rating;
import fashionable.simba.yanawaserver.rating.dto.RatingRequest;
import fashionable.simba.yanawaserver.rating.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request) {
        Rating response = ratingService.createRating(request);

        return ResponseEntity.ok(response);
    }
}

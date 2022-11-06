package fashionable.simba.yanawaserver.review.service;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import fashionable.simba.yanawaserver.members.domain.MemberUpdateReview;
import fashionable.simba.yanawaserver.review.domain.MannerTemperatureType;
import fashionable.simba.yanawaserver.review.domain.RatingScore;
import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.dto.ReviewRequest;
import fashionable.simba.yanawaserver.review.exception.NoMatchingDataException;
import fashionable.simba.yanawaserver.review.exception.NoParticipationDataException;
import fashionable.simba.yanawaserver.review.infra.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final ParticipationRepository participationRepository;
    private final MemberUpdateReview memberUpdateReview;

    public ReviewService(ReviewRepository reviewRepository, RecruitmentRepository recruitmentRepository, ParticipationRepository participationRepository, MemberUpdateReview memberUpdateReview) {
        this.reviewRepository = reviewRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.participationRepository = participationRepository;
        this.memberUpdateReview = memberUpdateReview;
    }

    public Review createReview(ReviewRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId()).orElseThrow(() -> new NoMatchingDataException("매칭 정보가 없습니다."));
        Participation participation = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getParticipantId()).orElseThrow(() -> new NoParticipationDataException("해당 매칭에 참여자 정보가 없습니다."));
        Participation writer = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getWriterId()).orElseThrow(() -> new NoParticipationDataException("해당 매칭에 리뷰 작성자 정보가 없습니다."));

        Review savedReview = new Review(request.getId(), participation.getUserId(), recruitment.getId(), new RatingScore(request.getRatingScore()), request.getMannerTemperatureType(), writer.getUserId(), request.getDetail());
        reviewRepository.save(savedReview);

        memberUpdateReview.updateRating(participation.getUserId(), calculateRating(participation.getUserId()));
        memberUpdateReview.updateManner(participation.getUserId(), calculateMannerTemperature(request.getMannerTemperatureType()));

        return savedReview;
    }

    public BigDecimal calculateRating(Long userId) {
        BigDecimal sumRatings = BigDecimal.ZERO;
        List<Review> reviews = reviewRepository.findByParticipantId(userId);

        for (Review review : reviews) {
            sumRatings = sumRatings.add(review.getRatingScore().getScore());
        }

        BigDecimal averageRating = sumRatings.divide(BigDecimal.valueOf(reviews.size()));

        BigDecimal level = BigDecimal.valueOf((Math.floor(averageRating.doubleValue() * 2)) / 2);

        return level;
    }

    public BigDecimal calculateMannerTemperature(MannerTemperatureType mannerTemperatureType) {

        if (mannerTemperatureType == MannerTemperatureType.EXCELLENT) {
            return BigDecimal.valueOf(0.1);
        } else if (mannerTemperatureType == MannerTemperatureType.BAD) {
            return BigDecimal.valueOf(-0.1);
        } else {
            return BigDecimal.valueOf(0);
        }
    }

}

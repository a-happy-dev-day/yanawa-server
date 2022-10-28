package fashionable.simba.yanawaserver.review.service;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import fashionable.simba.yanawaserver.review.domain.Review;
import fashionable.simba.yanawaserver.review.domain.RatingScore;
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

    public ReviewService(ReviewRepository reviewRepository, RecruitmentRepository recruitmentRepository, ParticipationRepository participationRepository) {
        this.reviewRepository = reviewRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.participationRepository = participationRepository;
    }

    public Review createReview(ReviewRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId()).orElseThrow(() -> new NoMatchingDataException("매칭 정보가 없습니다."));
        Participation participation = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getParticipantId()).orElseThrow( () -> new NoParticipationDataException("해당 매칭에 참여자 정보가 없습니다."));
        Participation user = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getUserId()).orElseThrow(() -> new NoParticipationDataException("해당 매칭에 리뷰 작성자 정보가 없습니다."));

        Review savedReview = new Review(request.getId(), request.getParticipantId(), request.getRecruitmentId(), new RatingScore(request.getRatingScore()), request.getMannerTemperatureType(), request.getUserId(), request.getDetail());
        reviewRepository.save(savedReview);

        //TODO: memberRepository.updateRating
        //TODO: memberRepository.updateManner

        return savedReview;
    }

}

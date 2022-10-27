package fashionable.simba.yanawaserver.rating.service;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import fashionable.simba.yanawaserver.rating.domain.Rating;
import fashionable.simba.yanawaserver.rating.domain.RatingScore;
import fashionable.simba.yanawaserver.rating.dto.RatingRequest;
import fashionable.simba.yanawaserver.rating.exception.NoMatchingDataException;
import fashionable.simba.yanawaserver.rating.exception.NoParticipationDataException;
import fashionable.simba.yanawaserver.rating.infra.RatingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final ParticipationRepository participationRepository;

    public RatingService(RatingRepository ratingRepository, RecruitmentRepository recruitmentRepository, ParticipationRepository participationRepository) {
        this.ratingRepository = ratingRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.participationRepository = participationRepository;
    }

    public Rating createRating(RatingRequest request) {
        Recruitment recruitment = recruitmentRepository.findById(request.getRecruitmentId()).orElseThrow(() -> new NoMatchingDataException("매칭 정보가 없습니다."));
        Participation participation = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getParticipantId()).orElseThrow( () -> new NoParticipationDataException("해당 매칭에 참여자 정보가 없습니다."));
        Participation user = participationRepository.findByMatchingIdAndUserId(request.getRecruitmentId(), request.getUserId()).orElseThrow(() -> new NoParticipationDataException("해당 매칭에 리뷰 작성자 정보가 없습니다."));

        Rating savedRating = new Rating(request.getId(), request.getParticipantId(), request.getRecruitmentId(), new RatingScore(request.getRatingScore()), request.getMannerTemperatureType(), request.getUserId(), request.getDetail());
        ratingRepository.save(savedRating);

        return savedRating;
    }

    public BigDecimal findAverageRating(long userId) {
        List<Rating> ratings = ratingRepository.findByParticipantId(userId);

        BigDecimal sum = BigDecimal.ZERO;
        for (Rating rating : ratings) {
            sum = sum.add(rating.getRatingScore().getScore());
        }

        return sum.divide(BigDecimal.valueOf(ratings.size()));
    }

}

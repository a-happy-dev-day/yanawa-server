package fashionable.simba.yanawaserver.review;

import fashionable.simba.yanawaserver.matching.domain.Participation;
import fashionable.simba.yanawaserver.matching.domain.ParticipationRepository;
import fashionable.simba.yanawaserver.matching.domain.Recruitment;
import fashionable.simba.yanawaserver.matching.domain.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Rating createRating(Rating rating) {
        Optional<Recruitment> recruitment = recruitmentRepository.findById(rating.getRecruitmentId());
        if (recruitment.isEmpty()) {
            throw new IllegalArgumentException("매칭 정보가 없습니다.");
        }
        Optional<Participation> participation = participationRepository.findByMatchingIdAndUserId(rating.getRecruitmentId(), rating.getParticipantId());
        if (participation.isEmpty()) {
            throw new IllegalArgumentException("해당 매칭에 참여자 정보가 없습니다.");
        }
        Optional<Participation> user = participationRepository.findByMatchingIdAndUserId(rating.getRecruitmentId(), rating.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("해당 매칭에 리뷰 작성자 정보가 없습니다.");
        }

        Rating savedRating = new Rating(rating.getId(), rating.getParticipantId(), rating.getRecruitmentId(), rating.getRatingScore(), rating.getMannerTemperature(), rating.getUserId(), rating.getDetail());
        ratingRepository.save(savedRating);

        return savedRating;
    }
}

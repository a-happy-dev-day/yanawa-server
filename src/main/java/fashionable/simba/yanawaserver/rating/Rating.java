package fashionable.simba.yanawaserver.review;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 후기 식별자
    private Long participantId; // 게임에 참여하는 참여자의 식별자
    private Long recruitmentId; // 모집의 고유 식별자
    @Embedded
    private RatingScore ratingScore; // 사용자의 실력 점수
    private MannerTemperatureType mannerTemperature; // 사용자의 매너 점수
    private Long userId; // 사용자의 정보를 확인할 수 있는 식별자
    private String detail;

    protected Rating() {
        /*no-op*/
    }

    public Rating(Long id, Long participantId, Long recruitmentId, RatingScore ratingScore, MannerTemperatureType mannerTemperature, Long userId, String detail) {
        this.id = id;
        this.participantId = participantId;
        this.recruitmentId = recruitmentId;
        this.ratingScore = ratingScore;
        this.mannerTemperature = mannerTemperature;
        this.userId = userId;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public RatingScore getRatingScore() {
        return ratingScore;
    }

    public MannerTemperatureType getMannerTemperature() {
        return mannerTemperature;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDetail() {
        return detail;
    }

    public void editRatingScore(RatingScore ratingScore) {
        this.ratingScore = ratingScore;
    }

    public void editMannerTemperature(MannerTemperatureType mannerTemperature) {
        this.mannerTemperature = mannerTemperature;
    }
}

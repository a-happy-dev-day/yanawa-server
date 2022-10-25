package fashionable.simba.yanawaserver.rating;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long participantId;
    private Long recruitmentId;
    @Embedded
    private RatingScore ratingScore;
    private MannerTemperatureType mannerTemperature;
    private Long userId;
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

package fashionable.simba.yanawaserver.review.dto;

import fashionable.simba.yanawaserver.review.domain.MannerTemperatureType;

import java.math.BigDecimal;

public class ReviewRequest {
    private Long id;
    private Long participantId;
    private Long recruitmentId;
    private BigDecimal ratingScore;
    private MannerTemperatureType mannerTemperatureType;
    private Long userId;
    private String detail;

    private ReviewRequest() {
        /*no-op*/
    }

    public ReviewRequest(Long id, Long participantId, Long recruitmentId, BigDecimal ratingScore, MannerTemperatureType mannerTemperatureType, Long userId, String detail) {
        this.id = id;
        this.participantId = participantId;
        this.recruitmentId = recruitmentId;
        this.ratingScore = ratingScore;
        this.mannerTemperatureType = mannerTemperatureType;
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

    public BigDecimal getRatingScore() {
        return ratingScore;
    }

    public MannerTemperatureType getMannerTemperatureType() {
        return mannerTemperatureType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDetail() {
        return detail;
    }
}

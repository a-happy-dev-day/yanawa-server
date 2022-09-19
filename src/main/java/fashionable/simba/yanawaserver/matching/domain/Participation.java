package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;

import java.time.LocalDateTime;

public class Participation {
    private Long id;
    private Long userId;
    private Long recruitmentId;
    private ParticipationStatusType status;
    private LocalDateTime requestDateTime;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public ParticipationStatusType getStatus() {
        return status;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public Participation changeAcceptedParticipation() {
        this.status = ParticipationStatusType.ACCEPTED;
        return this;
    }

    public Participation changeRejectedParticipation() {
        this.status = ParticipationStatusType.REJECTED;
        return this;
    }

    public Participation(Long id, Long userId, Long recruitmnetId, LocalDateTime requestDateTime, ParticipationStatusType status) {
        if (recruitmnetId == null) {
            throw new IllegalArgumentException("모집 정보를 찾을 수 없습니다.");
        }
        this.id = id;
        this.userId = userId;
        this.recruitmentId = recruitmnetId;
        this.requestDateTime = requestDateTime;
        this.status = status;
    }

    public Participation(Long userId, Long recruitmentId, LocalDateTime requestDateTime, ParticipationStatusType status) {
        this(null, userId, recruitmentId, requestDateTime, status);
    }
}

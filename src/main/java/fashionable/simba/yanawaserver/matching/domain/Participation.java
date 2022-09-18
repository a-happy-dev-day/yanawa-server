package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.ParticipationStatusType;
import fashionable.simba.yanawaserver.matching.error.NoMatchingDataException;

import java.time.LocalDateTime;

public class Participation {
    private Long id;
    private Long userId;
    private Long matchingId;
    private ParticipationStatusType status;
    private LocalDateTime requestDateTime;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMatchingId() {
        return matchingId;
    }

    public ParticipationStatusType getStatus() {
        return status;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void changeAcceptedParticipation() {
        this.status = ParticipationStatusType.ACCEPTED;
    }

    public Participation(Long id, Long userId, Long matchingId, LocalDateTime requestDateTime, ParticipationStatusType status) {
        if (matchingId == null) {
            throw new NoMatchingDataException("매칭 정보를 찾을 수 없습니다.");
        }
        this.id = id;
        this.userId = userId;
        this.matchingId = matchingId;
        this.requestDateTime = requestDateTime;
        this.status = status;
    }
}

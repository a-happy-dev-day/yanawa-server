package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;
import fashionable.simba.yanawaserver.matching.error.NoMatchingDataException;

import java.time.LocalDateTime;

public class Participation {
    private Long id;
    private Long userId;
    private Long matchingId;
    private RequestStatusType status;
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

    public RequestStatusType getStatus() {
        return status;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setStatus(RequestStatusType status) {
        this.status = status;
    }

    public Participation(Builder builder) {
        if (builder.matchingId == null) {
            throw new NoMatchingDataException("매칭 정보를 찾을 수 없습니다.");
        }

        this.id = builder.id;
        this.userId = builder.userId;
        this.matchingId = builder.matchingId;
        this.status = builder.status;
        this.requestDateTime = builder.requestDateTime;
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private Long matchingId;
        private RequestStatusType status;
        private LocalDateTime requestDateTime;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder matchingId(Long matchingId) {
            this.matchingId = matchingId;
            return this;
        }

        public Builder status(RequestStatusType status) {
            this.status = status;
            return this;
        }

        public Builder requestDateTime(LocalDateTime requestDateTime) {
            this.requestDateTime = requestDateTime;
            return this;
        }

        public Participation build() {
            return new Participation(this);
        }
    }
}

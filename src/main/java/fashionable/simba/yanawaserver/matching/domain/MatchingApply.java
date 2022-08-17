package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.RequestStatusType;

import java.time.LocalDateTime;

public class MatchingApply {
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

    public MatchingApply(MatchingRequestBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.matchingId = builder.matchingId;
        this.status = builder.status;
        this.requestDateTime = builder.requestDateTime;
    }

    public static class MatchingRequestBuilder {
        private Long id;
        private Long userId;
        private Long matchingId;
        private RequestStatusType status;
        private LocalDateTime requestDateTime;

        public MatchingRequestBuilder(Long id, Long userId, Long matchingId) {
            this.id = id;
            this.userId = userId;
            this.matchingId = matchingId;
        }

        public MatchingRequestBuilder setStatus(RequestStatusType status) {
            this.status = status;
            return this;
        }

        public MatchingRequestBuilder setRequestDateTime(LocalDateTime requestDateTime) {
            this.requestDateTime = requestDateTime;
            return this;
        }

        public MatchingApply build() {
            return new MatchingApply(this);
        }
    }
}

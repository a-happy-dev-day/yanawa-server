package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.domain.constant.RequestStatusType;

import java.time.LocalDateTime;
import java.util.UUID;

public class MatchingApply {
    private final UUID userId;
    private final UUID matchingId;
    private RequestStatusType status;
    private LocalDateTime requestDateTime;

    public UUID getUserId() {
        return userId;
    }

    public UUID getMatchingId() {
        return matchingId;
    }

    public RequestStatusType getStatus() {
        return status;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public MatchingApply(MatchingRequestBuilder builder) {
        this.userId = builder.userId;
        this.matchingId = builder.matchingId;
        this.status = builder.status;
        this.requestDateTime = builder.requestDateTime;
    }

    public static class MatchingRequestBuilder {
        private final UUID userId;
        private final UUID matchingId;
        private RequestStatusType status;
        private LocalDateTime requestDateTime;

        public MatchingRequestBuilder(UUID userId, UUID matchingId) {
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

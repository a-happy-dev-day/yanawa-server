package fashionable.simba.yanawaserver.domain;

import fashionable.simba.yanawaserver.constant.RequestStatusType;

import java.time.LocalDateTime;
import java.util.UUID;

public class MatchingApply {
    private final UUID userId;
    private final UUID hostId;
    private RequestStatusType status;
    private LocalDateTime requestDateTime;

    public UUID getUserId() {
        return userId;
    }

    public UUID getHostId() {
        return hostId;
    }

    public RequestStatusType getStatus() {
        return status;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public MatchingApply(MatchingRequestBuilder builder) {
        this.userId = builder.userId;
        this.hostId = builder.hostId;
        this.status = builder.status;
        this.requestDateTime = builder.requestDateTime;
    }

    public static class MatchingRequestBuilder {
        private final UUID userId;
        private final UUID hostId;
        private RequestStatusType status;
        private LocalDateTime requestDateTime;

        public MatchingRequestBuilder(UUID userId, UUID hostId) {
            this.userId = userId;
            this.hostId = hostId;
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

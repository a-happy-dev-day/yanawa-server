package fashionable.simba.yanawaserver.domain;

import java.util.UUID;

public class MatchingReview {
    private final UUID writerId;
    private final UUID partnerId;
    private String details;

    public UUID getWriterId() {
        return writerId;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public String getDetails() {
        return details;
    }

    public MatchingReview(MatchingReviewBuilder builder) {
        this.writerId = builder.writerId;
        this.partnerId = builder.partnerId;
        this.details = builder.details;
    }

    public static class MatchingReviewBuilder {
        private final UUID writerId;
        private final UUID partnerId;
        private String details;

        public MatchingReviewBuilder(UUID writerId, UUID partnerId) {
            this.writerId = writerId;
            this.partnerId = partnerId;
        }

        public MatchingReviewBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public MatchingReview build() {
            return new MatchingReview(this);
        }
    }
}

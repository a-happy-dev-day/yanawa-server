package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.NoPlayerDataException;

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

    public MatchingReview(Builder builder) {
        if (builder.writerId == null || builder.partnerId == null) {
            throw new NoPlayerDataException("");
        }

        this.writerId = builder.writerId;
        this.partnerId = builder.partnerId;
        this.details = builder.details;
    }

    public static class Builder {
        private UUID writerId;
        private UUID partnerId;
        private String details;

        public Builder() {
            // **no option
        }

        public Builder writerId(UUID writerId) {
            this.writerId = writerId;
            return this;
        }

        public Builder partnerId(UUID partnerId) {
            this.partnerId = partnerId;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public MatchingReview build() {
            return new MatchingReview(this);
        }
    }
}

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
        this.writerId = builder.writerId;
        this.partnerId = builder.partnerId;
        this.details = builder.details;
    }

    public static class Builder {
        private final UUID writerId;
        private final UUID partnerId;
        private String details;

        public Builder(UUID writerId, UUID partnerId) {
            if (writerId == null || partnerId == null) {
                throw new NoPlayerDataException();
            }
            this.writerId = writerId;
            this.partnerId = partnerId;
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

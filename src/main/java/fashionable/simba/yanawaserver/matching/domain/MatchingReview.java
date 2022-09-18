package fashionable.simba.yanawaserver.matching.domain;

public class MatchingReview {
    private Long id;
    private Long matchingId;
    private Long writerId;
    private Long partnerId;
    private String details;

    public Long getId() {
        return id;
    }

    public Long getMatchingId() {
        return matchingId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public String getDetails() {
        return details;
    }

    public MatchingReview(Long matchingId, Long writerId, Long partnerId, String details) {
        this.matchingId = matchingId;
        this.writerId = writerId;
        this.partnerId = partnerId;
        this.details = details;
    }

    public MatchingReview(Long id, Long matchingId, Long writerId, Long partnerId, String details) {
        this.id = id;
        this.matchingId = matchingId;
        this.writerId = writerId;
        this.partnerId = partnerId;
        this.details = details;
    }
}

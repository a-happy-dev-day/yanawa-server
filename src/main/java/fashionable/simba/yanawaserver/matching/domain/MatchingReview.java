package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.error.NoPlayerDataException;

public class MatchingReview {
    private final Long writerId;
    private final Long partnerId;
    private String details;

    public Long getWriterId() {
        return writerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public String getDetails() {
        return details;
    }

    public MatchingReview(Long writerId, Long partnerId, String details) {
        if (writerId == null || partnerId == null) {
            throw new NoPlayerDataException("사용자 정보가 없어 리뷰를 작성할 수 없습니다.");
        }
        this.writerId = writerId;
        this.partnerId = partnerId;
        this.details = details;
    }
}

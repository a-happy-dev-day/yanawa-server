package fashionable.simba.yanawaserver.global.token.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ValidAccessToken {
    @Id
    private Long userId;
    private String accessToken;

    protected ValidAccessToken() {
    }

    public ValidAccessToken(Long userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

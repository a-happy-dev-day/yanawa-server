package fashionable.simba.yanawaserver.global.token.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ValidRefreshToken {
    @Id
    private Long userId;
    private String refreshToken;

    protected ValidRefreshToken() {/*no-op*/}

    public ValidRefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserId() {
        return userId;
    }
}

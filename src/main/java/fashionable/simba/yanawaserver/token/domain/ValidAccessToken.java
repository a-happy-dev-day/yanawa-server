package fashionable.simba.yanawaserver.token.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidAccessToken that = (ValidAccessToken) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}

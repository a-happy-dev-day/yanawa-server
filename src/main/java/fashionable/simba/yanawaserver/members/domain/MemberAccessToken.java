package fashionable.simba.yanawaserver.members.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class MemberAccessToken implements AccessToken {
    private String tokenType;
    private String refreshToken;

    protected MemberAccessToken() {/*no-op*/}

    public MemberAccessToken(String tokenType, String refreshToken) {
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberAccessToken that = (MemberAccessToken) o;
        return Objects.equals(getTokenType(), that.getTokenType()) && Objects.equals(getRefreshToken(), that.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTokenType(), getRefreshToken());
    }
}

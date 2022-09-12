package fashionable.simba.yanawaserver.members.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;

import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class KakaoAccessToken implements AccessToken {
    private String tokenType;
    private String accessToken;
    private Date expiresIn;
    private String refreshToken;
    private Date refreshTokenExpiresIn;

    protected KakaoAccessToken() {/*no-op*/}

    public KakaoAccessToken(String tokenType, String accessToken, Date expiresIn, String refreshToken, Date refreshTokenExpiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setRefreshTokenExpiresIn(Date refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }
    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public Date getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public Date getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KakaoAccessToken that = (KakaoAccessToken) o;
        return Objects.equals(getTokenType(), that.getTokenType()) && Objects.equals(getAccessToken(), that.getAccessToken()) && Objects.equals(getRefreshToken(), that.getRefreshToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTokenType(), getAccessToken(), getRefreshToken());
    }
}
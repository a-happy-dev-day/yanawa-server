package fashionable.simba.yanawaserver.auth.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import fashionable.simba.yanawaserver.auth.filter.UserInfo;

import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class KakaoUserInfo implements UserInfo {
    private Long id;
    private Map<String, String> properties;
    private Map<String, Object> kakaoAccount;

    private KakaoUserInfo() {
    }

    public KakaoUserInfo(Long id, Map<String, String> properties, Map<String, Object> kakaoAccount) {
        this.id = id;
        this.properties = properties;
        this.kakaoAccount = kakaoAccount;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNickname() {
        return properties.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getProfileImage() {
        return properties.get("profile_image");
    }

    @Override
    public String getThumbnailImage() {
        return properties.get("thumbnail_image");
    }

    @Override
    public String toString() {
        return "KakaoUserInfo{" +
            "id=" + id +
            ", properties=" + properties +
            ", kakaoAccount=" + kakaoAccount +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KakaoUserInfo that = (KakaoUserInfo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

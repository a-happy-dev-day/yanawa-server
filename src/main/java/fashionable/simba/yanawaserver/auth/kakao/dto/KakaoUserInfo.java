package fashionable.simba.yanawaserver.auth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import fashionable.simba.yanawaserver.auth.domain.UserInfo;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class KakaoUserInfo implements UserInfo {
    private Long id;
    private Map<String, String> properties;
    private Map<String, Object> kakaoAccount;

    private KakaoUserInfo() {/*no-op*/}

    public KakaoUserInfo(Long id, Map<String, String> properties, Map<String, Object> kakaoAccount) {
        this.id = id;
        this.properties = properties;
        this.kakaoAccount = kakaoAccount;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public Map<String, Object> getKakaoAccount() {
        return kakaoAccount;
    }

    @Override
    public String getNickname() {
        return properties.get("nickname");
    }

    @Override
    public String getEmail() {
        if (kakaoAccount.containsKey("email")) {
            return (String) kakaoAccount.get("email");
        }
        return "none";
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
}

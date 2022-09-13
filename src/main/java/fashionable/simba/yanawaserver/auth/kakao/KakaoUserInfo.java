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
}

package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("kakao")
public class KakaoMember extends Member {
    private Long kakaoId;
    private String nickname;
    private String profileImageUrl;
    private String thumbnailImageUrl;
    @Embedded
    private KakaoAccessToken kakaoAccessToken;

    protected KakaoMember() {/*no-op*/}

    public KakaoMember(String email, List<String> roles, Long kakaoId, String nickname, String profileImageUrl, String thumbnailImageUrl, KakaoAccessToken kakaoAccessToken) {
        super(email, roles);
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.kakaoAccessToken = kakaoAccessToken;
    }

    public KakaoMember(Long kakaoId, String email, String nickname, String profileImageUrl, String thumbnailImageUrl, KakaoAccessToken kakaoAccessToken) {
        super(email, List.of(RoleType.ROLE_MEMBER.name()));
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.kakaoAccessToken = kakaoAccessToken;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getKakaoId() {
        return kakaoId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public KakaoAccessToken getKakaoAccessToken() {
        return kakaoAccessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KakaoMember that = (KakaoMember) o;
        return Objects.equals(getKakaoId(), that.getKakaoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKakaoId());
    }
}

package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("kakao")
public final class KakaoMember extends Member {
    private Long kakaoId;
    private String profileImageUrl;
    private String thumbnailImageUrl;

    protected KakaoMember() {/*no-op*/}

    public KakaoMember(Long id, String email, List<String> roles, Long kakaoId, String nickname, String profileImageUrl, String thumbnailImageUrl, boolean isFirst) {
        super(nickname, id, email, roles, isFirst);
        this.kakaoId = kakaoId;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public KakaoMember(Long kakaoId, String email, String nickname, String profileImageUrl, String thumbnailImageUrl, boolean isFirst) {
        this(null, email, List.of(RoleType.ROLE_MEMBER.name()), kakaoId, nickname, profileImageUrl, thumbnailImageUrl, isFirst);
    }

    public String getNickname() {
        return super.getNickname();
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
}

package fashionable.simba.yanawaserver.auth.kakao;

import fashionable.simba.yanawaserver.auth.filter.AccessToken;

import java.util.Objects;

public class KakaoMember {
    private Long kakaoId;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String thumbnailImageUrl;
    private AccessToken accessToken;

    public KakaoMember(Long kakaoId, String email, String nickname, String profileImageUrl, String thumbnailImageUrl, AccessToken accessToken) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
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

    public AccessToken getAccessToken() {
        return accessToken;
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

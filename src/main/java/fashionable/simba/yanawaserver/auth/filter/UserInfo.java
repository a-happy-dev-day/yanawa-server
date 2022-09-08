package fashionable.simba.yanawaserver.auth.filter;

public interface UserInfo {
    Long getId();

    String getNickname();

    String getEmail();

    String getProfileImage();

    String getThumbnailImage();

    boolean equals(Object o);

    int hashCode();
}

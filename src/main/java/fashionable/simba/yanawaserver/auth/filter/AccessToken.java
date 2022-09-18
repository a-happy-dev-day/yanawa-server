package fashionable.simba.yanawaserver.auth.filter;

public interface AccessToken {

    String getTokenType();

    String getRefreshToken();

    boolean equals(Object o);

    int hashCode();
}

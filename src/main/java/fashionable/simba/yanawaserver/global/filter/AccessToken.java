package fashionable.simba.yanawaserver.global.filter;

public interface AccessToken {

    String getTokenType();

    String getRefreshToken();

    boolean equals(Object o);

    int hashCode();
}

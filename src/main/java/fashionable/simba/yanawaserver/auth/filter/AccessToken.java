package fashionable.simba.yanawaserver.auth.filter;

import java.util.Date;

public interface AccessToken {

    String getTokenType();

    String getAccessToken();

    Date getExpiresIn();

    String getRefreshToken();

    Date getRefreshTokenExpiresIn();

    boolean equals(Object o);

    int hashCode();
}

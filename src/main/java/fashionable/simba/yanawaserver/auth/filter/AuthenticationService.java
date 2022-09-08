package fashionable.simba.yanawaserver.auth.filter;

import fashionable.simba.yanawaserver.members.domain.KakaoUser;

public interface AuthenticationService {
    AccessToken getAccessToken(AccessCode code);

    KakaoUser getUserInfo(AccessToken token);

    AccessToken refreshToken(AccessToken token);

    void logout(AccessToken token);

}

package fashionable.simba.yanawaserver.auth.filter;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;

public interface AuthenticationService {
    AccessToken getAccessToken(AccessCode code);

    KakaoMember getUserInfo(AccessToken token);

    AccessToken refreshToken(AccessToken token);

    void logout(AccessToken token);

}

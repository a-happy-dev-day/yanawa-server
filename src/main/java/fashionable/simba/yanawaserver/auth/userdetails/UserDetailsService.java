package fashionable.simba.yanawaserver.auth.userdetails;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String principal);

    UserDetails saveKakaoMember(KakaoMember member);

    boolean isValidRefreshToken(String username, String refreshToken);

    boolean isValidUser(String username);
}

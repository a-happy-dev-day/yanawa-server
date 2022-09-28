package fashionable.simba.yanawaserver.global.userdetails;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String principal);

    UserDetails saveKakaoMember(KakaoMember member);

    boolean isValidUser(String username);
}

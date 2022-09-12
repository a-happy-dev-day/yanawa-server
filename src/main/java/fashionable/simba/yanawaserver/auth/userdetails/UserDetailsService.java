package fashionable.simba.yanawaserver.auth.userdetails;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String principal);

    UserDetails saveAndLoadKakaoMember(KakaoMember member);
}

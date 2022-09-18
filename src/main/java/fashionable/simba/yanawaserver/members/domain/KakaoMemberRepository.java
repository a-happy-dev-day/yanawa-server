package fashionable.simba.yanawaserver.members.domain;

import java.util.Optional;

public interface KakaoMemberRepository {

    Optional<KakaoMember> findByKakaoId(Long kakaoId);

    KakaoMember save(KakaoMember kakaoMember);
}

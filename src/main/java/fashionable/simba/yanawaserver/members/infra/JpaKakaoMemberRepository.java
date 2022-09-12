package fashionable.simba.yanawaserver.members.infra;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.KakaoMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaKakaoMemberRepository extends JpaRepository<KakaoMember, Long>, KakaoMemberRepository {

    Optional<KakaoMember> findByKakaoId(Long kakaoId);
}

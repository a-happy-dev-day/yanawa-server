package fashionable.simba.yanawaserver.members.infra;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.KakaoMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaKakaoMemberRepository extends JpaRepository<KakaoMember, Long>, KakaoMemberRepository {
    @Override
    Optional<KakaoMember> findByKakaoId(Long kakaoId);

    @Override
    KakaoMember save(KakaoMember entity);
}

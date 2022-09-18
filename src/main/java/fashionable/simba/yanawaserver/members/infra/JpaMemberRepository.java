package fashionable.simba.yanawaserver.members.infra;

import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    <S extends Member> Optional<S> findById(Long id);

    @Override
    <S extends Member> S save(S entity);

    @Override
    List<Member> findAll();

    Optional<KakaoMember> findByKakaoId(Long kakaoId);
}

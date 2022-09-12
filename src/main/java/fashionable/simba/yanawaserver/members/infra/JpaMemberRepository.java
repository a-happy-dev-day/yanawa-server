package fashionable.simba.yanawaserver.members.infra;

import fashionable.simba.yanawaserver.members.domain.Member;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Optional<Member> findByEmail(String email);

    void deleteByEmail(String email);

    @Override
    <S extends Member> S save(S entity);

    @Override
    List<Member> findAll();

}

package fashionable.simba.yanawaserver.members.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberRepository {
    <S extends Member> Optional<S> findById(Long id);

    <S extends Member> S save(S entity);

    List<Member> findAll();

    <S extends Member> Optional<S> findByKakaoId(Long kakaoId);

}

package fashionable.simba.yanawaserver.members.domain;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findByEmail(String email);

    void deleteByEmail(String email);

    <S extends Member> S save(S entity);

}

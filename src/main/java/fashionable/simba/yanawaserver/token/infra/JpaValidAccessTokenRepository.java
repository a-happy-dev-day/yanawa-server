package fashionable.simba.yanawaserver.token.infra;

import fashionable.simba.yanawaserver.token.domain.ValidAccessToken;
import fashionable.simba.yanawaserver.token.domain.ValidAccessTokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaValidAccessTokenRepository extends JpaRepository<ValidAccessToken, Long>, ValidAccessTokenStorage {
    @Override
    <S extends ValidAccessToken> S save(S entity);

    @Override
    Optional<ValidAccessToken> findByAccessToken(String accessToken);

    @Override
    void delete(ValidAccessToken entity);

    @Override
    Optional<ValidAccessToken> findById(Long s);
}

package fashionable.simba.yanawaserver.global.token.infra;

import fashionable.simba.yanawaserver.global.token.domain.ValidRefreshToken;
import fashionable.simba.yanawaserver.global.token.domain.ValidRefreshTokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaValidRefreshTokenRepository extends JpaRepository<ValidRefreshToken, Long>, ValidRefreshTokenStorage {

    @Override
    <S extends ValidRefreshToken> S save(S entity);

    @Override
    Optional<ValidRefreshToken> findById(Long userId);

    @Override
    Optional<ValidRefreshToken> findByRefreshToken(String refreshToken);

    @Override
    void delete(ValidRefreshToken entity);
}

package fashionable.simba.yanawaserver.global.token.infra;

import fashionable.simba.yanawaserver.global.token.domain.ValidRefreshToken;
import fashionable.simba.yanawaserver.global.token.domain.ValidRefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaValidRefreshTokenRepository extends JpaRepository<ValidRefreshToken, String>, ValidRefreshTokenRepository {

    @Override
    boolean existsById(String refreshToken);

    @Override
    ValidRefreshToken save(ValidRefreshToken refreshToken);
}

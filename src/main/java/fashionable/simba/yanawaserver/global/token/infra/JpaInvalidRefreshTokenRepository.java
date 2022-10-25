package fashionable.simba.yanawaserver.global.token.infra;

import fashionable.simba.yanawaserver.global.token.domain.InvalidRefreshToken;
import fashionable.simba.yanawaserver.global.token.domain.InvalidRefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvalidRefreshTokenRepository extends JpaRepository<InvalidRefreshToken, String>, InvalidRefreshTokenRepository {

    @Override
    boolean existsById(String refreshToken);

    @Override
    InvalidRefreshToken save(InvalidRefreshToken refreshToken);
}

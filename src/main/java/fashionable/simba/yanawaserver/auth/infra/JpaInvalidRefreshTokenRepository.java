package fashionable.simba.yanawaserver.auth.infra;

import fashionable.simba.yanawaserver.auth.domain.InvalidRefreshToken;
import fashionable.simba.yanawaserver.auth.domain.InvalidRefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvalidRefreshTokenRepository extends JpaRepository<InvalidRefreshToken, String>, InvalidRefreshTokenRepository {

    @Override
    boolean existsById(String refreshToken);

    @Override
    InvalidRefreshToken save(InvalidRefreshToken refreshToken);
}

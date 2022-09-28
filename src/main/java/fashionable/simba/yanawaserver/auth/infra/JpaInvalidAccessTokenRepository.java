package fashionable.simba.yanawaserver.auth.infra;

import fashionable.simba.yanawaserver.auth.domain.InvalidAccessToken;
import fashionable.simba.yanawaserver.auth.domain.InvalidAccessTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvalidAccessTokenRepository extends JpaRepository<InvalidAccessToken, String>, InvalidAccessTokenRepository {

    @Override
    boolean existsById(String accessToken);

    @Override
    InvalidAccessToken save(InvalidAccessToken accessToken);
}

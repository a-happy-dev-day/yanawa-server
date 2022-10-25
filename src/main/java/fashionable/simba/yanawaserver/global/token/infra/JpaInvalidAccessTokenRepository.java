package fashionable.simba.yanawaserver.global.token.infra;

import fashionable.simba.yanawaserver.global.token.domain.InvalidAccessToken;
import fashionable.simba.yanawaserver.global.token.domain.InvalidAccessTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvalidAccessTokenRepository extends JpaRepository<InvalidAccessToken, String>, InvalidAccessTokenRepository {

    @Override
    boolean existsById(String accessToken);

    @Override
    InvalidAccessToken save(InvalidAccessToken accessToken);
}

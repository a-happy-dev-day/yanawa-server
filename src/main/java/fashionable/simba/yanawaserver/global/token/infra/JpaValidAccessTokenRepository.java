package fashionable.simba.yanawaserver.global.token.infra;

import fashionable.simba.yanawaserver.global.token.domain.ValidAccessToken;
import fashionable.simba.yanawaserver.global.token.domain.ValidAccessTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaValidAccessTokenRepository extends JpaRepository<ValidAccessToken, String>, ValidAccessTokenRepository {

    @Override
    boolean existsById(String accessToken);

    @Override
    ValidAccessToken save(ValidAccessToken accessToken);
}

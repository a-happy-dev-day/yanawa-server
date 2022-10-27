package fashionable.simba.yanawaserver.global.token.domain;

import java.util.Optional;

public interface ValidAccessTokenStorage {
    Optional<ValidAccessToken> findByAccessToken(String accessToken);

    <S extends ValidAccessToken> S save(S entity);

    void delete(ValidAccessToken entity);

    Optional<ValidAccessToken> findById(Long s);
}

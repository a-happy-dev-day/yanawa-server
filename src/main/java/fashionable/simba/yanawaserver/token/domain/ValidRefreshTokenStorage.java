package fashionable.simba.yanawaserver.token.domain;

import java.util.Optional;

public interface ValidRefreshTokenStorage {
    <S extends ValidRefreshToken> S save(S entity);

    void delete(ValidRefreshToken entity);

    Optional<ValidRefreshToken> findById(Long userId);

    Optional<ValidRefreshToken> findByRefreshToken(String refreshToken);

}

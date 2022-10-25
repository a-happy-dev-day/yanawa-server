package fashionable.simba.yanawaserver.global.token.domain;

public interface InvalidRefreshTokenRepository {
    boolean existsById(String refreshToken);

    InvalidRefreshToken save(InvalidRefreshToken refreshToken);
}

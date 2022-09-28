package fashionable.simba.yanawaserver.auth.domain;

public interface InvalidRefreshTokenRepository {
    boolean existsById(String refreshToken);

    InvalidRefreshToken save(InvalidRefreshToken refreshToken);
}

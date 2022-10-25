package fashionable.simba.yanawaserver.global.token.domain;

public interface ValidRefreshTokenRepository {
    boolean existsById(String refreshToken);

    ValidRefreshToken save(ValidRefreshToken refreshToken);
}

package fashionable.simba.yanawaserver.global.token.domain;

public interface ValidAccessTokenRepository {
    boolean existsById(String accessToken);

    ValidAccessToken save(ValidAccessToken accessToken);
}

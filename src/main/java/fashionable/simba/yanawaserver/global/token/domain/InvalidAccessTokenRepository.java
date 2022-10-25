package fashionable.simba.yanawaserver.global.token.domain;

public interface InvalidAccessTokenRepository {
    boolean existsById(String accessToken);

    InvalidAccessToken save(InvalidAccessToken accessToken);
}

package fashionable.simba.yanawaserver.auth.domain;

public interface InvalidAccessTokenRepository {
    boolean existsById(String accessToken);

    InvalidAccessToken save(InvalidAccessToken accessToken);
}

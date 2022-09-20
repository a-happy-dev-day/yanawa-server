package fashionable.simba.yanawaserver.auth.domain;

public interface InvalidRefreshTokenRepository {
    boolean exist(String accessToken);

    void save(String accessToken);
}

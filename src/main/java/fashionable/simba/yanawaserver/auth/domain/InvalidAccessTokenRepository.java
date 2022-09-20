package fashionable.simba.yanawaserver.auth.domain;

public interface InvalidAccessTokenRepository {
    boolean exist(String accessToken);

    void save(String accessToken);
}

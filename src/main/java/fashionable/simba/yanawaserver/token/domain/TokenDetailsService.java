package fashionable.simba.yanawaserver.token.domain;

public interface TokenDetailsService {
    void validateAccessToken(String accessToken);

    void validateRefreshToken(String refreshToken);
}

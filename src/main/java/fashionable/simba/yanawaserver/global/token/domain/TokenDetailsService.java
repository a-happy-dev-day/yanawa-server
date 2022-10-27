package fashionable.simba.yanawaserver.global.token.domain;

public interface TokenDetailsService {
    void validateAccessToken(String accessToken);

    void validateRefreshToken(String refreshToken);
}

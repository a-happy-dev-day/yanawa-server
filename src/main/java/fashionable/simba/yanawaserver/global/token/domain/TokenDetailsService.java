package fashionable.simba.yanawaserver.global.token.domain;

public interface TokenDetailsService {
    boolean validateAccessToken(String accessToken);

    boolean validateRefreshToken(String refreshToken);
}

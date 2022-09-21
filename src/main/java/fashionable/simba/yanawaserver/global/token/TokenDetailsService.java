package fashionable.simba.yanawaserver.global.token;

public interface TokenDetailsService {
    boolean validateAccessToken(String accessToken);

    boolean validateRefreshToken(String refreshToken);
}

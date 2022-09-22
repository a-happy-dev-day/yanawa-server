package fashionable.simba.yanawaserver.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "security.jwt.token")
public class JwtPropertiesConfigurer {
    private final String secretKey;
    private final String refreshKey;
    private final long validityAccessTokenMilliseconds;
    private final long validityRefreshTokenMilliseconds;

    public JwtPropertiesConfigurer(String secretKey, String refreshKey, long validityAccessTokenMilliseconds, long validityRefreshTokenMilliseconds) {
        this.secretKey = secretKey;
        this.refreshKey = refreshKey;
        this.validityAccessTokenMilliseconds = validityAccessTokenMilliseconds;
        this.validityRefreshTokenMilliseconds = validityRefreshTokenMilliseconds;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getRefreshKey() {
        return refreshKey;
    }

    public long getValidityAccessTokenMilliseconds() {
        return validityAccessTokenMilliseconds;
    }

    public long getValidityRefreshTokenMilliseconds() {
        return validityRefreshTokenMilliseconds;
    }
}

package fashionable.simba.yanawaserver.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "kakao.client")
@ConstructorBinding
public class KakaoPropertiesConfigurer {
    private String redirectUri;
    private String clientId;
    private String secretKey;

    public KakaoPropertiesConfigurer(String redirectUri, String clientId, String secretKey) {
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.secretKey = secretKey;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

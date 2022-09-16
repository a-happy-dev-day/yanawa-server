package fashionable.simba.yanawaserver.auth.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoConfigurer {
    private final KakaoAuthorizationClient kakaoAuthorizationClient;
    private final KakaoAuthenticationClient kakaoAuthenticationClient;
    private final String redirectUri;
    private final String clientId;
    private final String secretKey;

    public KakaoConfigurer(KakaoAuthorizationClient kakaoAuthorizationClient,
                           KakaoAuthenticationClient kakaoAuthenticationClient,
                           @Value("${kakao.client.redirect-uri}") String redirectUri,
                           @Value("${kakao.client.id}") String clientId,
                           @Value("${kakao.client.secret}") String secretKey) {
        this.kakaoAuthorizationClient = kakaoAuthorizationClient;
        this.kakaoAuthenticationClient = kakaoAuthenticationClient;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.secretKey = secretKey;
    }

    @Bean
    KakaoAuthenticationService kakaoAuthenticationService() {
        return new KakaoAuthenticationService(kakaoAuthenticationClient, kakaoAuthorizationClient, redirectUri, clientId, secretKey);
    }

}


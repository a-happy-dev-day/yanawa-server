package fashionable.simba.yanawaserver.auth.kakao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoConfigurer {
    private final KakaoAuthorizationClient kakaoAuthorizationClient;
    private final KakaoAuthenticationClient kakaoAuthenticationClient;
    private final KakaoPropertiesConfigurer kakaoPropertiesConfigurer;

    public KakaoConfigurer(KakaoAuthorizationClient kakaoAuthorizationClient, KakaoAuthenticationClient kakaoAuthenticationClient, KakaoPropertiesConfigurer kakaoPropertiesConfigurer) {
        this.kakaoAuthorizationClient = kakaoAuthorizationClient;
        this.kakaoAuthenticationClient = kakaoAuthenticationClient;
        this.kakaoPropertiesConfigurer = kakaoPropertiesConfigurer;
    }

    @Bean
    KakaoAuthenticationService kakaoAuthenticationService() {
        return new KakaoAuthenticationService(
            kakaoAuthenticationClient,
            kakaoAuthorizationClient,
            kakaoPropertiesConfigurer.getRedirectUri(),
            kakaoPropertiesConfigurer.getClientId(),
            kakaoPropertiesConfigurer.getSecretKey()
        );
    }


}


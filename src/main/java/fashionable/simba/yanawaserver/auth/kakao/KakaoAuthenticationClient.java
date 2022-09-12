package fashionable.simba.yanawaserver.auth.kakao;

import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")
public interface KakaoAuthenticationClient {

    @PostMapping(value = "/oauth/token", consumes = "application/json")
    ResponseEntity<KakaoAccessToken> getToken(
        @RequestHeader("Content-Type") String contentType,
        @RequestParam("grant_type") String grantType,
        @RequestParam("client_id") String clientId,
        @RequestParam("redirect_uri") String redirectUri,
        @RequestParam("code") String code,
        @RequestParam("client_secret") String clientSecret
    );
}

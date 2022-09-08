package fashionable.simba.yanawaserver.auth.kakao;

import fashionable.simba.yanawaserver.auth.filter.AuthorizationClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoClient")
public interface KakaoAuthorizationClient extends AuthorizationClient {

    @GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<KakaoUserInfo> getUserInfo(
        @RequestHeader("Content-type") String contentType,
        @RequestHeader("Authorization") String code
    );

}

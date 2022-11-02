package fashionable.simba.yanawaserver.oauth.infra;

import fashionable.simba.yanawaserver.oauth.infra.dto.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(url = "https://kapi.kakao.com", name = "kakaoClient")
public interface KakaoAuthorizationClient {

    @GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<KakaoUserInfo> getUserInfo(
        @RequestHeader("Content-type") String contentType,
        @RequestHeader("Authorization") String code
    );

}

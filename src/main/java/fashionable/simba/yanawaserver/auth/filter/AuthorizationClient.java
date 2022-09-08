package fashionable.simba.yanawaserver.auth.filter;

import org.springframework.http.ResponseEntity;

public interface AuthorizationClient {
    ResponseEntity<? extends UserInfo> getUserInfo(String contentType, String code);
}

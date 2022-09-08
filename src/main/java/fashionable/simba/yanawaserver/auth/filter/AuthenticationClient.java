package fashionable.simba.yanawaserver.auth.filter;

import org.springframework.http.ResponseEntity;

public interface AuthenticationClient {
    ResponseEntity<? extends AccessToken> getToken();
}

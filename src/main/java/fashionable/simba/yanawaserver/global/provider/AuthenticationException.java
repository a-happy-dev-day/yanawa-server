package fashionable.simba.yanawaserver.global.provider;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        this("ACCESS DENIED");
    }
}

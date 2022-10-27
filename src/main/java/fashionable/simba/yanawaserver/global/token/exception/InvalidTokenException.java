package fashionable.simba.yanawaserver.global.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }

    public static InvalidTokenException expiresToken() {
        return new InvalidTokenException("유효하지 않은 토큰입니다.");
    }

    public static InvalidTokenException invalidAccessToken() {
        return new InvalidTokenException("유효하지 않은 액세스 토큰 입니다.");
    }

    public static InvalidTokenException invalidRefreshToken() {
        return new InvalidTokenException("유효하지 않은 리프레시 토큰 입니다.");
    }

    public static InvalidTokenException invalidUser() {
        return new InvalidTokenException("사용자 정보가 존재하지 않습니다.");
    }

    public static InvalidTokenException invalidTokenType() {
        return new InvalidTokenException("유효하지 않은 Principal 입니다.");
    }
}
